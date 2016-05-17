
public class MapperCalculator {
	private int spillSize;
	private float spillPercentage;
	private int nrOfSamples = 100;
	
	public MapperCalculator(int spillSize, float spillPercentage) {
		this.spillSize = spillSize;
		this.spillPercentage = spillPercentage;
	}
	
	public int CalculateMappers(MapperContext ctxt){
		int recordSizeBytes = calculateMaxSize(ctxt);
		
		System.out.println("Max size of record: " + recordSizeBytes + " bytes");
		
		// Calculate number of records before spill is full
		float sizeToFill = spillSize * spillPercentage * 1024 * 1024;
		System.out.println("Size to fill: " + sizeToFill + " bytes");
		
		int nrOfRecordsPerMapper = (int) Math.floor(sizeToFill / recordSizeBytes); 
		System.out.println("Number of records per mapper: " + nrOfRecordsPerMapper);
		
		int nrOfMappers = (int) Math.ceil((double) ctxt.getNrOfRecords() / nrOfRecordsPerMapper);
		System.out.println("Number of mappers: " + nrOfMappers);
		
		ctxt.setNrOfMappers(nrOfMappers);
		return ctxt.getNrOfMappers();
	}
	
	// returns the max size of one record in bytes
	private int calculateMaxSize(MapperContext ctxt) {
		UserGenerator gen = UserGenerator.makeUserGenerator(ctxt);
		
		int firstRecordNr = ctxt.getFirstLineNumber();
		int lastRecordNr = ctxt.getNrOfRecords() - 1;
		int maxSize = 0;
		
		for (int i = firstRecordNr; i < firstRecordNr + nrOfSamples; i++) {
			ctxt.setRecordNr(firstRecordNr + i);
			maxSize = Math.max(maxSize, gen.generate().length());
		}
		
		for (int i = lastRecordNr - nrOfSamples; i < lastRecordNr + 1; i++) {
			ctxt.setRecordNr(lastRecordNr - nrOfSamples + i);
			maxSize = Math.max(maxSize, gen.generate().length());
		}
		
		ctxt.setRecordNr(0);
		return maxSize;
	}
}
