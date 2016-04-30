
public abstract class UserGenerator {
	protected MapperContext mapperContext;
	
	public UserGenerator() {}
	
	public UserGenerator(MapperContext mapperContext) { this.mapperContext = mapperContext; } 
		
	public MapperContext getMapperContext() {
		return mapperContext;
	}

	public void setMapperContext(MapperContext mapperContext) {
		this.mapperContext = mapperContext;
	}

	public abstract String generate();
}
