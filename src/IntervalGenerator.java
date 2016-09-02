public class IntervalGenerator extends UserGenerator {
	
	@Override
	public String generate() {
		SingleAttributeRange singleAttributeRange = mapperContext.getSingleAttributeRanges().get(mapperContext.getMapperId());
		
		double upperbound = singleAttributeRange.getRange().getMaximumDouble();
		double lowerbound = singleAttributeRange.getRange().getMinimumDouble();
		
		int var =  (int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound); 
//		System.out.println("Mapper: " + mapperContext.getMapperId() + ", range: " + singleAttributeRange + ", var:" + var);
		return "" + var;
	}
}
