import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Set;

import org.apache.directory.api.util.Base64;

public class MapperContext implements java.io.Serializable {
	private String recordGenerator = null;
	private ArrayList<String> AttributeGenerators = new ArrayList<>();
	private String delimiter = " | ";
	private int nrOfAttributes;
	private int nrOfMappers;
	private int nrOfRecords;
	private int mapperId;
	private int firstLineNumber;
	private int recordNr;
	private int nrOfRecordsForThisMapper;
	private ArrayList<ChanceClique> chanceCliques;
	private ArrayList<Set<String>> cliqueOrder;

	@Override
	public String toString() {
		return "MapperContext [recordGenerator=" + recordGenerator + ", AttributeGenerators=" + AttributeGenerators
				+ ", delimiter=" + delimiter + ", nrOfAttributes=" + nrOfAttributes + ", nrOfMappers=" + nrOfMappers
				+ ", nrOfRecords=" + nrOfRecords + ", mapperId=" + mapperId + ", firstLineNumber=" + firstLineNumber
				+ ", recordNr=" + recordNr + ", nrOfRecordsForThisMapper=" + nrOfRecordsForThisMapper
				+ ", chanceCliques=" + chanceCliques + ", cliqueOrder=" + cliqueOrder + "]";
	}

	public String getRecordGenerator() {
		return recordGenerator;
	}

	public void setRecordGenerator(String recordGenerator) {
		this.recordGenerator = recordGenerator;
	}

	public ArrayList<String> getAttributeGenerators() {
		return AttributeGenerators;
	}

	public void setAttributeGenerators(ArrayList<String> attributeGenerators) {
		AttributeGenerators = attributeGenerators;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

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
	
	public ArrayList<ChanceClique> getChanceCliques() {
		return chanceCliques;
	}

	public void setChanceCliques(ArrayList<ChanceClique> chanceCliques) {
		this.chanceCliques = chanceCliques;
	}

	public ArrayList<Set<String>> getCliqueOrder() {
		return cliqueOrder;
	}

	public void setCliqueOrder(ArrayList<Set<String>> cliqueOrder) {
		this.cliqueOrder = cliqueOrder;
	}	
	
	public static String serializeContext(MapperContext context) {
		String serializedObject = "";

		 // serialize the object
		 try {
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     ObjectOutputStream so = new ObjectOutputStream(bo);
		     so.writeObject(context);
		     so.flush();
//		     serializedObject = bo.toString();
		     serializedObject = new String(Base64.encode(bo.toByteArray()));
		 } catch (Exception e) {
		     System.out.println(e);
		 }
		 
		 return serializedObject;
	}
	
	public static MapperContext deserializeContext(String serializedObject) {
		MapperContext obj = null;
		try {
//		     byte b[] = serializedObject.getBytes(); 
		     byte b[] = Base64.decode(serializedObject.toCharArray()); 
		     ByteArrayInputStream bi = new ByteArrayInputStream(b);
		     ObjectInputStream si = new ObjectInputStream(bi);
		     obj = (MapperContext) si.readObject();
		 } catch (Exception e) {
		     System.out.println(e);
		 }
		
		return obj;
	}
}
