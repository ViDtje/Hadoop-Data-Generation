
public class MyUserGenerator extends UserGenerator {

	@Override
	public String generate() {
		// TODO Auto-generated method stub
		return "" + mapperContext.getRecordNr() * mapperContext.getRecordNr();
	}

}