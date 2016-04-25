

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MainJob {
	private int nrOfMappers = 4;
	private int nrOfRecords = 1000;
	private String inputPath = "mappers/";
	private String outputPath = "output"; // timestamp gets added in the last folder
	private String mapperPath = "/mappers/";
	
	public void createJob(String[] args) throws Exception{
		if (args.length >= 2) {
			nrOfMappers = Integer.parseInt(args[0]);
			nrOfRecords = Integer.parseInt(args[1]);
		}
		
		System.out.println("nrOfMappers: " + nrOfMappers);
		System.out.println("nrOfRecords: " + nrOfRecords);
		Configuration conf = new Configuration();
	    conf.set("DataGen.nrOfRecords", nrOfRecords + "");
	    conf.set("DataGen.nrOfMappers", nrOfMappers + "");
	    
		
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
		
//		job.waitForCompletion(true);
		
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
		// TODO make on HDFS
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
		
		
		
		
		// Create mapperFiles using Java API
//		for (int i = 0; i < nrOfMappers; i++) {			
//			String path = mapperPath + " " + i + ".mapper";
//			File f = new File(path);
//			f.getParentFile().mkdirs(); 
//			try {
//				f.createNewFile();
//				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
//				writer.write("" + i);
//				writer.close();
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
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
}
