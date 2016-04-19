

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.google.common.io.Files;

public class MainJob {
	private static int nrOfMappers = 10;
	private static int nrOfRecords = 100;
	private static String inputPath = "input/";
	private static String outputPath = "output"; // timestamp gets added in the last folder
	private static String mapperPath = "mappers/";
	
	public static void main(String[] args) throws Exception {
		Job job = new Job();
		job.setJarByClass(MainJob.class);
		job.setJobName("Generate Data");
		
		makeOutputPath();
		createMapperFiles();
		
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		job.setMapperClass(GenerationMapper.class);
//		job.setReducerClass(RandomDataReducer.class);
		job.setNumReduceTasks(1);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		if (!job.waitForCompletion(true))
			System.exit(0);
		
		// Delete mappers dir
		FileUtils.deleteDirectory(new File(mapperPath));
	}
	
	private static void makeOutputPath() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
		outputPath += " " + timeStamp + "/";
	}
	
	private static void createMapperFiles() {
		for (int i = 0; i < nrOfMappers; i++) {			
			String path = mapperPath + " " + i + ".mapper";
			File f = new File(path);
			f.getParentFile().mkdirs(); 
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
