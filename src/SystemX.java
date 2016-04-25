import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SystemX extends Configured implements Tool {

	
	
	@Override
	public int run(String[] arg0) throws Exception {
		MainJob job = new MainJob();
		job.createJob(arg0);
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new SystemX(), args);
		System.exit(res);
	}
}
