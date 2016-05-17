
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
	
	public static UserGenerator makeUserGenerator(MapperContext ctxt) {
		UserGenerator gen = null;
		if (ctxt.getRecordGenerator() != null)
			gen = makeRecordGenerator(ctxt);
		else
			gen = makeAttributeGenerator();
		
		gen.setMapperContext(ctxt);
		return gen;
	}
	

	private static UserGenerator makeRecordGenerator(MapperContext ctxt) {
		Class c = null;
		try {	
			c = Class.forName(ctxt.getRecordGenerator());
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
		return gen;
	}
	
	private static UserGenerator makeAttributeGenerator() {
		return new AttributeGenerator();
	}
}
