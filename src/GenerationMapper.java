

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GenerationMapper extends Mapper<LongWritable, Text, Text, Text> {
	private int nrOfRecords;
	private int nrOfMappers;
	private int mapId;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        nrOfRecords = Integer.parseInt(conf.get("DataGen.nrOfRecords"));
        nrOfMappers = Integer.parseInt(conf.get("DataGen.nrOfMappers"));
	}
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {		
		mapId = Integer.parseInt(value.toString());
		
		int nrOfRecordsForThisMapper = nrOfRecords/nrOfMappers;
		int firstLineNr = mapId * nrOfRecordsForThisMapper;		
		
		UserGenerator gen = new UserGenerator(mapId);
		for (int i = 0; i < nrOfRecordsForThisMapper; i++) {
			context.write(new Text("Mapper ID: " + mapId), new Text(gen.generate(firstLineNr + i)));
		}
	}
}
