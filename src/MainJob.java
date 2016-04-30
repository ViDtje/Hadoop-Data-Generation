

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.tools.GetConf;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


// TODO werk met ControlledJobs en JobControl
public class MainJob {
	private int nrOfMappers = 1;
	private int nrOfRecords = 1000;
	private int nrOfAttributes = 1;
	private String inputPath = "mappers/";
	private String outputPath = "output"; // timestamp gets added in the last folder
	private String mapperPath = "/mappers/";
	
	public void createJob(String[] args, Configuration conf) throws Exception{
		if (args.length >= 2) {
			nrOfMappers = Integer.parseInt(args[0]);
			nrOfRecords = Integer.parseInt(args[1]);
		}
			
		System.out.println("nrOfMappers: " + nrOfMappers);
		System.out.println("nrOfRecords: " + nrOfRecords);
	
//		Configuration conf = 
	    conf.set("DataGen.nrOfRecords", nrOfRecords + "");
	    conf.set("DataGen.nrOfMappers", nrOfMappers + "");
	    conf.set("Datagen.relation.nrOfAttributes", nrOfAttributes+ "");
	    conf.set("Datagen.relation.generatorClass", "UserGenerator");
	    
	    System.out.println("map spill buffer: "+ conf.get("mapreduce.task.io.sort.mb"));
	    System.out.println("map spill percent: " + conf.get("mapreduce.map.sort.spill.percent"));
	   		
		Job job = new Job(conf);
		job.setJarByClass(MainJob.class);
		job.setJobName("Generate Data");
		
		makeOutputPath();
		createMapperFiles(conf);
		
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		job.setMapperClass(GenerationMapper.class);
		job.setNumReduceTasks(0);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
				
		if (!job.waitForCompletion(true))
			System.exit(0);
		
		// Delete mappers dir
//		FileUtils.deleteDirectory(new File(mapperPath));
		deleteMapperFiles(conf);
		
	}
	
	private void makeOutputPath() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
		outputPath += " " + timeStamp + "/";
	}
	
	private void createMapperFiles(Configuration conf) throws Exception {
		// Create mapperFiles using Hadoop API
		FileSystem hdfs = FileSystem.get(conf);
		Path workingDir = hdfs.getWorkingDirectory();
		Path mapperDir = new Path(mapperPath);
		mapperDir = Path.mergePaths(workingDir, mapperDir);
		
		// Delete mapperDir if exists
		if (hdfs.exists(mapperDir))
			hdfs.delete(mapperDir);
		
		// make mapperDir
		hdfs.mkdirs(mapperDir);
		
		for (int i = 0; i < nrOfMappers; i++) {
			// Create each mapper file
			Path newFilePath = Path.mergePaths(mapperDir, new Path("/" + i + ".mapper"));
			hdfs.createNewFile(newFilePath);
			
			// Fill each mapper file with it's id
			FSDataOutputStream fsOutStream = hdfs.create(newFilePath);
			fsOutStream.write(new String(i + "").getBytes());
			fsOutStream.close();
		}
	}
	
	private void deleteMapperFiles(Configuration conf) throws Exception {
		FileSystem hdfs = FileSystem.get(conf);
		Path workingDir = hdfs.getWorkingDirectory();
		Path mapperDir = new Path(mapperPath);
		mapperDir = Path.mergePaths(workingDir, mapperDir);
		
		// Delete mapperDir if exists
		if (hdfs.exists(mapperDir))
			hdfs.delete(mapperDir);
	}
	
	private void solverTest() {
//		RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 2, 3, -2 }, { -1, 7, 6 }, { 4, -3, -5 } }, false);
		RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 } }, false);
//		RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 } }, false);
		DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();
//		RealVector constants = new ArrayRealVector(new double[] { 1, -2, 1 }, false);
		RealVector constants = new ArrayRealVector(new double[] { 1, 1, 1 }, false);
		RealVector solution = solver.solve(constants);
		System.out.println(solution.getEntry(0));
		System.out.println(solution.getEntry(1));
		System.out.println(solution.getEntry(2));
		System.out.println(solution.getEntry(3));
		System.out.println(solution.getEntry(4));
		System.out.println(solution.getEntry(5));
	}
}
