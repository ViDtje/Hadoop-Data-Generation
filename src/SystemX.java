
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SystemX extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
		Graph g = new Graph();
		g.constraintTests();
		
		int res = 0;
//		int res = ToolRunner.run(new SystemX(), args);
		System.exit(res);
	}
	
	@Override
	public int run(String[] arg0) throws Exception {
		JobControl jobctrl = new JobControl("DataGeneration");
		
		DataGenJob job = new DataGenJob(parseArguments(arg0), getConf());
		ControlledJob cJob = job.createJob();
		cJob.getJob().setJarByClass(getClass());
		jobctrl.addJob(cJob);
		
		// Start all jobs 
		Thread t = new Thread(jobctrl);
		t.start();
		
		// Wait until all jobs are finished
		while (!jobctrl.allFinished()){
//			System.out.println("Still running...");
			Thread.yield();
	        Thread.sleep(5000);
		}
		
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
		int nrOfMappers = 1;
//		int nrOfRecords = 1000000000;
		int nrOfRecords = 1000;
		int nrOfAttributes = 1;
		
		if (args.length >= 2) {
			nrOfMappers = Integer.parseInt(args[0]);
			nrOfRecords = Integer.parseInt(args[1]);
		}
		
		
		
		MapperContext mapCtxt = new MapperContext();
		mapCtxt.setNrOfRecords(nrOfRecords);
		mapCtxt.setNrOfMappers(nrOfMappers);
		mapCtxt.setNrOfAttributes(nrOfAttributes);
		mapCtxt.getAttributeGenerators().add("SquareGenerator");
		mapCtxt.getAttributeGenerators().add("SumGenerator");
		
		JobContext jobCtxt = new JobContext();
		jobCtxt.setMapCtxt(mapCtxt);
		
		return jobCtxt;
	}
}
 

//private void solverTest() {
////	RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 2, 3, -2 }, { -1, 7, 6 }, { 4, -3, -5 } }, false);
//	RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 } }, false);
////	RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
////		{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 } }, false);
//	DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();
////	RealVector constants = new ArrayRealVector(new double[] { 1, -2, 1 }, false);
//	RealVector constants = new ArrayRealVector(new double[] { 1, 1, 1 }, false);
//	RealVector solution = solver.solve(constants);
//	System.out.println(solution.getEntry(0));
//	System.out.println(solution.getEntry(1));
//	System.out.println(solution.getEntry(2));
//	System.out.println(solution.getEntry(3));
//	System.out.println(solution.getEntry(4));
//	System.out.println(solution.getEntry(5));
//}