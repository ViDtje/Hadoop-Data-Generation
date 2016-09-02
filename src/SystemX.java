
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import antlr.ConstraintGrammarLexer;
import antlr.ConstraintGrammarParser;
import antlr.InputToConstraintVisitor;
import predicate.Constraint;
import predicate.IntervalPredicate;

public class SystemX extends Configured implements Tool {
	private ArrayList<String> attributes;
	private int nrOfRecords = 0;
	private int lowerBound = 0;
	private int upperBound = 0;
	
	public static void main(String[] args) throws Exception {
//		int res = 0;
//		// Simplex solver
//        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 1, 1, 1, 1 }, 0);
//        
//        ArrayList<LinearConstraint> constraints = new ArrayList<>();
//		constraints.add(new LinearConstraint(new double[] {1, 1, 1, 1}, Relationship.EQ, 50));
//        constraints.add(new LinearConstraint(new double[] {0, 1, 1, 0}, Relationship.EQ, 30));
//        constraints.add(new LinearConstraint(new double[] {0, 0, 1, 1}, Relationship.EQ, 40));
//        SimplexSolver solver = new SimplexSolver();
//        PointValuePair solution = solver.optimize(new MaxIter(100), f, new LinearConstraintSet(constraints), GoalType.MAXIMIZE, new NonNegativeConstraint(true));
//        
//        System.out.println(Arrays.toString(solution.getPoint()));
		
		int res = ToolRunner.run(new SystemX(), args);
		System.exit(res);
	}
	
	@Override
	public int run(String[] arg0) throws Exception {		
//		long totalStartTime = System.currentTimeMillis();
		JobControl jobctrl = new JobControl("DataGeneration");
		
		DataGenJob job = new DataGenJob(parseArguments(arg0), getConf());
		ControlledJob cJob = job.createJob();
		cJob.getJob().setJarByClass(getClass());
		jobctrl.addJob(cJob);
		
		
		
		// Start all jobs 
		Thread t = new Thread(jobctrl);
		t.start();
//		System.out.println("Startime");
		long startTime = System.currentTimeMillis();
		
		// Wait until all jobs are finished
		while (!jobctrl.allFinished()){
//			System.out.println("Still running...");
			Thread.yield();
//	        Thread.sleep(5000);
		}
		long endTime = System.currentTimeMillis();
		double seconds = (endTime - startTime);
		System.out.println("Generation step duration: " + seconds + " milliseconds.");
//		System.out.println("Total duration: " + (endTime - totalStartTime) + " milliseconds.");
		
		// Print all the stats per job
		for (ControlledJob obj : jobctrl.getSuccessfulJobList())  {
			System.out.println("JobCounters from job: " + obj.getJob().getCounters());
		}
		
		// Print all the stats per job
		for (ControlledJob obj : jobctrl.getFailedJobList())  {
			System.out.println("JobCounters from job: " + obj.getJob().getCounters());
		}
		
		// TODO cleanup all jobs
		job.cleanup();
		
		return 0;
	}

	private JobContext parseArguments(String[] args) {
		MapperContext mapCtxt = new MapperContext();
		String inputString;
		String fileInput;
//		inputString = "|A=0&&B=0|=5 \n |B=0&&C=0|=5 \n |C=0&&D=0|=5\n";
		inputString = "\n";
//		inputString = "|A=0&&B=0|=5 \n";
//		inputString = "|0<A|=5 \n |1>A|=5\n";
//		inputString = "|A=0 && B=1 && C=1| = 1 \n |A=1 && B=0 && C=0| = 1 \n |A=0 && B=1 && C=0| = 1";
//		inputString = "|A=0| = 2 \n |B=0 || C=1| = 2";
//		inputString = "|A1=0&&A2=0|=5 \n |A2=0&&A3=0|=5";
//		inputString = "|20<A<60|=30 \n |40<A<101|=40\n";
		
//		ArrayList<String> attrs = new ArrayList<>(Arrays.asList("A"));
//		ArrayList<String> attrs = new ArrayList<>();
		
//		int lim = 100;
//		System.out.println("Number of attributes: " + lim);
//		for (int i = 0; i < lim; i++)
//			attrs.add("A" + i);
		
//		int constraintLim = 101;
//		for (int i = 0; i < constraintLim - 1; i++)
//			inputString += "|" + attrs.get(i) + "=0&&" + attrs.get(i + 1) + "=0|=5\n";
//		
//		System.out.println("Number of constraints: " + (constraintLim - 1));
		int nrOfMappers = 5; // TODO MapperCalculator
		
		
		
		if (args.length >= 1) {
			try {
				inputString = readFile(args[0]);
				System.out.println("inputstring: " + inputString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("file input: " + args[0]);
		} else 
			System.exit(1);
		
		System.out.println("Attributes: " + attributes);
		
		// TODO read from file
		// read input 
		
		
		System.out.println("Number of records: " + nrOfRecords);
		
		ArrayList<Integer> domain = makeDomain(lowerBound, upperBound);
		System.out.println("domain: " + domain);
		long startTime = System.currentTimeMillis();
		ArrayList<Constraint> constraints = parseConstraints(inputString);
		System.out.println("Constraints: " + constraints);
		
		
		// TODO if naive add generators
//		mapCtxt.getAttributeGenerators().add("SquareGenerator");
//		mapCtxt.getAttributeGenerators().add("SumGenerator");
		
		// check if SA or MA
		boolean interval = false;
		if (attributes.size() == 1) {
			interval = true;
			for (Constraint c : constraints)
				if (!(c.getPredicate() instanceof IntervalPredicate))
					interval = false;
		}
		
		if (interval) {
			// use SA algorithm
			SingleAttributeSolver solver = new SingleAttributeSolver();
			ArrayList<SingleAttributeRange> ranges = solver.solve(domain, constraints, nrOfRecords);
//	        System.out.println(singleAttributeRanges);
	        nrOfMappers = ranges.size();
	        mapCtxt.setSingleAttributeRanges(ranges);
	        mapCtxt.setRecordGenerator("IntervalGenerator");
	        
	        long endTime = System.currentTimeMillis();
			double seconds = (endTime - startTime) ;
			System.out.println("Pre-processing step duration: " + seconds + " milliseconds.");
			
		} else {
			// use MA
			System.out.println("Multiple Attributes algorithm");
			
			Graph g = new Graph(attributes, nrOfRecords, domain);
			g.parseConstraints(constraints);
			long endTime = System.currentTimeMillis();
			double seconds = (endTime - startTime) ;
			ArrayList<ChanceClique> chanceCliques = g.getChanceCliques();
			ArrayList<Set<String>> cliqueOrder = g.getCliqueOrder();
			System.out.println("chanceClique" + chanceCliques);
//			System.out.println("cliqueOrder" + cliqueOrder);
			System.out.println("Pre-processing step duration: " + seconds + " milliseconds.");
			mapCtxt.setRecordGenerator("ConstraintGenerator");
			mapCtxt.setChanceCliques(g.getChanceCliques());
			mapCtxt.setCliqueOrder(g.getCliqueOrder());
		}

		mapCtxt.setNrOfRecords(nrOfRecords);
		mapCtxt.setNrOfMappers(nrOfMappers);
		mapCtxt.setNrOfAttributes(attributes.size());
		mapCtxt.setAttributes(attributes);
				
		
		JobContext jobCtxt = new JobContext();
		jobCtxt.setMapCtxt(mapCtxt);
//		jobCtxt.setOutputPath("/user/leuven/314/vsc31477/");
		
		return jobCtxt;
	}
	
	private ArrayList<Constraint> parseConstraints(String inputString) {
		// Antlr parsing
		ArrayList<Constraint> constraints;
		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(inputString);
		
		// create a lexer that feeds off of input CharStream
		ConstraintGrammarLexer lexer = new ConstraintGrammarLexer(input); 
		
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		
		// create a parser that feeds off the tokens buffer
		ConstraintGrammarParser parser = new ConstraintGrammarParser(tokens);
		
		ParseTree tree = parser.constraints(); // begin parsing at constraints rule		
		
		InputToConstraintVisitor vis = new InputToConstraintVisitor();
		vis.visit(tree);
		constraints = vis.getConstraints(); 
		
		return constraints;
	}
	
	private ArrayList<Integer> makeDomain(int lowerBound, int upperBound) {
		ArrayList<Integer> domain = new ArrayList<>();
		for (int i = lowerBound; i <= upperBound; i++)
			domain.add(i);
		return domain;
	}
	
	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	        	// Relation
	        	if  (line.startsWith("R(") && line.endsWith(")")) {
	        		line = line.substring(2, line.length() - 1);
	        		System.out.println("line.split: " + Arrays.asList(line.split(",")));
	        		attributes = new ArrayList<>(Arrays.asList(line.split(",")));
	        		System.out.println("attr line: " + attributes);
	        	}
	        		
	        	
	        	// nrOfTuples
	        	else if (line.startsWith("|R|=")) {
	        		line = line.substring(4,line.length());
	        		nrOfRecords = Integer.parseInt(line);
	        	}
	        	
	        	// Domain
	        	else if (line.startsWith("[") && line.endsWith("]")) {
	        		line = line.substring(1, line.length() - 1);
	        		String [] arr = line.split(",");
	        		lowerBound = Integer.parseInt(arr[0]);
	        		upperBound = Integer.parseInt(arr[1]);
	        	}
	        			
	        	else {
	        		stringBuilder.append(line);
	        		stringBuilder.append(ls);
	        	}
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
}