

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GenerationMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//		context.getConfiguration().get()
		
		String line = value.toString();
//		System.out.println(line);
		context.write(new Text(line), new IntWritable((int) (Math.random() * 100))); // Indien we enkel mapper gebruiken
	}
}
