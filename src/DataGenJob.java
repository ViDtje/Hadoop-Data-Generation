

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


// TODO werk met ControlledJobs en JobControl
public class DataGenJob {
	JobContext jobCtxt;
	MapperContext mapCtxt;
	Configuration conf;
	
	public DataGenJob(JobContext jobCtxt, Configuration conf) {
		this.jobCtxt = jobCtxt;
		mapCtxt = jobCtxt.getMapCtxt();
		this.conf = conf;
	}
	
	public ControlledJob createJob() throws Exception{
	    conf.set("Datagen.context", MapperContext.serializeContext(mapCtxt));
	    
		Job job = new Job(conf);
//		job.setJarByClass(getClass());
		job.setJobName("Generate Data " + jobCtxt.getJobNr());
		
		makeOutputPath();
		
//		int spillSize = Integer.parseInt(job.getConfiguration().get("mapreduce.task.io.sort.mb"));
//		float spillPercentage = Float.parseFloat(job.getConfiguration().get("mapreduce.map.sort.spill.percent"));
//		mapCtxt.setNrOfMappers(new MapperCalculator(spillSize, spillPercentage).CalculateMappers(mapCtxt));
		createMapperFiles(conf);
		
		FileInputFormat.addInputPath(job, new Path(jobCtxt.getInputPath()));
		FileOutputFormat.setOutputPath(job, new Path(jobCtxt.getOutputPath()));
		
		job.setMapperClass(GenerationMapper.class);
		job.setNumReduceTasks(0);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
				
		ControlledJob cJob = new ControlledJob(conf);
		cJob.setJob(job);
		
		System.out.println("Number of mappers: " + mapCtxt.getNrOfMappers());
		System.out.println("Number of records: " + mapCtxt.getNrOfRecords());
		// get special parameters, e.g. -Dtest 
	    // (put these first in arg list!)
//	    System.out.println("test parameter: " + conf.get("test"));
		
	    // Only works after the conf has been filled by the job
//		System.out.println("map spill buffer: "+ job.getConfiguration().get("mapreduce.task.io.sort.mb"));
//		System.out.println("map spill percent: " + job.getConfiguration().get("mapreduce.map.sort.spill.percent"));
		
		return cJob;
	}
	
	private void makeOutputPath() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
		jobCtxt.setOutputPath(jobCtxt.getOutputPath() + " " + timeStamp + "/");
	}
	
	private void createMapperFiles(Configuration conf) throws Exception {
		// Create mapperFiles using Hadoop API
		FileSystem hdfs = FileSystem.get(conf);
		Path workingDir = hdfs.getWorkingDirectory();
		Path mapperDir = new Path(jobCtxt.getMapperPath());
		mapperDir = Path.mergePaths(workingDir, mapperDir);
		
		// Delete mapperDir if exists
		if (hdfs.exists(mapperDir))
			hdfs.delete(mapperDir);
		
		// make mapperDir
		hdfs.mkdirs(mapperDir);
		
		for (int i = 0; i < mapCtxt.getNrOfMappers(); i++) {
			// Create each mapper file
			Path newFilePath = Path.mergePaths(mapperDir, new Path("/" + i + ".mapper"));
			hdfs.createNewFile(newFilePath);
			
			// Fill each mapper file with it's id
			FSDataOutputStream fsOutStream = hdfs.create(newFilePath);
			fsOutStream.write(new String(i + "").getBytes());
			fsOutStream.close();
		}
	}
	
	public void cleanup() throws Exception {
		deleteMapperFiles(this.conf);
	}
	
	private void deleteMapperFiles(Configuration conf) throws Exception {
		FileSystem hdfs = FileSystem.get(conf);
		Path workingDir = hdfs.getWorkingDirectory();
		Path mapperDir = new Path(jobCtxt.getMapperPath());
		mapperDir = Path.mergePaths(workingDir, mapperDir);
		
		// Delete mapperDir if exists
		if (hdfs.exists(mapperDir))
			hdfs.delete(mapperDir);
	}
}
