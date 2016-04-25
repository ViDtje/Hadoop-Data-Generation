
public class UserGenerator {
	private MapperContext mapperContext;
	
	public UserGenerator(MapperContext mapperContext) { this.mapperContext = mapperContext; } 
	
	public String generate() {
		return "" + mapperContext.getRecordNr() * mapperContext.getRecordNr(); 
	}
}
