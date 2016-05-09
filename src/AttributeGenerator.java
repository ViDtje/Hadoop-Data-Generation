import java.util.ArrayList;

public class AttributeGenerator extends UserGenerator{
	private ArrayList<UserGenerator> attrGenerators;
	
	@Override
	public String generate() {
		if (attrGenerators == null) 
			makeAttributeGenerators();
		
		String returnVal = "";
		for (int i = 0; i < attrGenerators.size() - 1; i++) 
			returnVal += attrGenerators.get(i).generate() + mapperContext.getDelimiter();
		
		returnVal += attrGenerators.get(attrGenerators.size() - 1).generate(); 
		
		return returnVal;
	}

	private void makeAttributeGenerators() {
		attrGenerators = new ArrayList<>();
		for (String s : mapperContext.getAttributeGenerators()) {
			Class c = null;
			try {
				c = Class.forName(s);
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
			attrGenerators.add(gen);
		}
	}
}
