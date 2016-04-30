

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GenerationMapper extends Mapper<LongWritable, Text, Text, Text> {
	private MapperContext mapperContext;
	private String generateFunction;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();        
        mapperContext = new MapperContext();
        mapperContext.setNrOfRecords(Integer.parseInt(conf.get("DataGen.nrOfRecords")));
        mapperContext.setNrOfMappers(Integer.parseInt(conf.get("DataGen.nrOfMappers")));
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {		
		mapperContext.setMapperId(Integer.parseInt(value.toString()));
		
		mapperContext.setNrOfRecordsForThisMapper(mapperContext.getNrOfRecords()/mapperContext.getNrOfMappers());
		mapperContext.setFirstLineNumber(mapperContext.getMapperId() * mapperContext.getNrOfRecords());		
		
//		UserGenerator gen = new UserGenerator(mapperContext);
		Class c = null;
		try {
			c = Class.forName("MyUserGenerator");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object obj = null;
		try {
			obj = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserGenerator gen = (UserGenerator) obj;
		gen.setMapperContext(mapperContext);
		
		for (int i = 0; i < mapperContext.getNrOfRecordsForThisMapper(); i++) {
			mapperContext.setRecordNr(mapperContext.getFirstLineNumber() + i);
			context.write(new Text("Mapper ID: " + mapperContext.getMapperId()), new Text(gen.generate()));
		}
	}
}
