import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SystemX extends Configured implements Tool {
	
	@Override
	public int run(String[] arg0) throws Exception {
		MainJob job = new MainJob();
		System.out.println("mapreduce spill size: " + getConf().get("mapreduce.task.io.sort.mb"));
		System.out.println("mapreduce spill percent: " + getConf().get("mapreduce.map.sort.spill.percent"));
		job.createJob(arg0, getConf());
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new SystemX(), args);
		System.exit(res);
	}
}
 