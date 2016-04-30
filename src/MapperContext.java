
public class MapperContext {
	private int nrOfMappers;
	private int nrOfRecords;
	private int mapperId;
	private int firstLineNumber;
	private int recordNr;
	private int nrOfRecordsForThisMapper;
	private int nrOfAttributes;
	
	public int getNrOfAttributes() {
		return nrOfAttributes;
	}
	public void setNrOfAttributes(int nrOfAttributes) {
		this.nrOfAttributes = nrOfAttributes;
	}
	public int getRecordNr() {
		return recordNr;
	}
	public void setRecordNr(int recordNr) {
		this.recordNr = recordNr;
	}
	public int getFirstLineNumber() {
		return firstLineNumber;
	}
	public void setFirstLineNumber(int firstLineNumber) {
		this.firstLineNumber = firstLineNumber;
	}
	public int getNrOfRecordsForThisMapper() {
		return nrOfRecordsForThisMapper;
	}
	public void setNrOfRecordsForThisMapper(int nrOfRecordsForThisMapper) {
		this.nrOfRecordsForThisMapper = nrOfRecordsForThisMapper;
	}
	public int getNrOfMappers() {
		return nrOfMappers;
	}
	public void setNrOfMappers(int nrOfMappers) {
		this.nrOfMappers = nrOfMappers;
	}
	public int getNrOfRecords() {
		return nrOfRecords;
	}
	public void setNrOfRecords(int nrOfRecords) {
		this.nrOfRecords = nrOfRecords;
	}
	public int getMapperId() {
		return mapperId;
	}
	public void setMapperId(int mapperId) {
		this.mapperId = mapperId;
	}
	
	
}
