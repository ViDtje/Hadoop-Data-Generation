
import java.util.ArrayList;
import java.util.Set;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SystemX extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
//		int res = 0;
		int res = ToolRunner.run(new SystemX(), args);
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

	// TODO read from file
	private JobContext parseArguments(String[] args) {
		String inputString;
		inputString = "|A1=0&&A2=0|=5 \n |A2=0&&A3=0|=5 \n |A3=0&&A4=0|=5\n";
		inputString = "|A=0 && B=1 && C=1| = 1 \n |A=1 && B=0 && C=0| = 1 \n |A=0 && B=1 && C=0| = 1";
		inputString = "|A=0| = 2 \n |B=0 || C=1| = 2";
		
		Graph g = new Graph();
		g.parseConstraints(inputString);
		ArrayList<ChanceClique> chanceCliques = g.getChanceCliques();
		ArrayList<Set<String>> cliqueOrder = g.getCliqueOrder();
		System.out.println("chanceClique" + chanceCliques);
		System.out.println("cliqueOrder" + cliqueOrder);
		
		int nrOfMappers = 1;
//		int nrOfRecords = 1000000000;
		int nrOfRecords = 1;
		int nrOfAttributes = 1;
		
		if (args.length >= 2) {
			nrOfMappers = Integer.parseInt(args[0]);
			nrOfRecords = Integer.parseInt(args[1]);
		}
		
		
		MapperContext mapCtxt = new MapperContext();
		mapCtxt.setNrOfRecords(nrOfRecords);
		mapCtxt.setNrOfMappers(nrOfMappers);
		mapCtxt.setNrOfAttributes(nrOfAttributes);
//		mapCtxt.getAttributeGenerators().add("SquareGenerator");
//		mapCtxt.getAttributeGenerators().add("SumGenerator");
		mapCtxt.setRecordGenerator("ConstraintGenerator");
		mapCtxt.setChanceCliques(g.getChanceCliques());
		mapCtxt.setCliqueOrder(g.getCliqueOrder());
		
		JobContext jobCtxt = new JobContext();
		jobCtxt.setMapCtxt(mapCtxt);
		
		return jobCtxt;
	}
}