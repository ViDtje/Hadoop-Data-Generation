
public class JobContext {
	private String inputPath = "mappers/";
	private String outputPath = "output"; // timestamp gets added in the last folder
	private String mapperPath = "/mappers/";
	private int JobNr = 0;
	private MapperContext mapCtxt;
	
	public String getInputPath() {
		return inputPath;
	}
	
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	public String getOutputPath() {
		return outputPath;
	}
	
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public String getMapperPath() {
		return mapperPath;
	}
	
	public void setMapperPath(String mapperPath) {
		this.mapperPath = mapperPath;
	}
	
	public int getJobNr() {
		return JobNr;
	}
	
	public void setJobNr(int jobNr) {
		JobNr = jobNr;
	}
	
	public MapperContext getMapCtxt() {
		return mapCtxt;
	}
	
	public void setMapCtxt(MapperContext mapCtxt) {
		this.mapCtxt = mapCtxt;
	}	
}
