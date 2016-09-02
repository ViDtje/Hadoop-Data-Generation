

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GenerationMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
	private MapperContext mapperContext;
	private String generateFunction;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();        
        mapperContext = MapperContext.deserializeContext(conf.get("Datagen.context"));
//        System.out.println("MapperContext: " + mapperContext);
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {		
		mapperContext.setMapperId(Integer.parseInt(value.toString()));
		
		if (mapperContext.getSingleAttributeRanges() != null)
			mapperContext.setNrOfRecordsForThisMapper(mapperContext.getSingleAttributeRanges().get(mapperContext.getMapperId()).getTimes());
		else
			mapperContext.setNrOfRecordsForThisMapper(mapperContext.getNrOfRecords()/mapperContext.getNrOfMappers());
		mapperContext.setFirstLineNumber(mapperContext.getMapperId() * mapperContext.getNrOfRecords());		
		
		UserGenerator gen = UserGenerator.makeUserGenerator(mapperContext);
		
		// Generate the data with the chosen generator
		for (int i = 0; i < mapperContext.getNrOfRecordsForThisMapper(); i++) {
			mapperContext.setRecordNr(mapperContext.getFirstLineNumber() + i);
			// TODO key nullwritable
			context.write(NullWritable.get(), new Text(gen.generate()));
		}
	}
}
