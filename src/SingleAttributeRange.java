import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang.math.IntRange;
import org.apache.directory.api.util.Base64;

public class SingleAttributeRange implements java.io.Serializable {
	private IntRange range;
	private int times;
	
	public SingleAttributeRange(IntRange range, int times) {
		this.range = range;
		this.times = times;
	}
	
	public IntRange getRange() {
		return range;
	}
	public void setRange(IntRange range) {
		this.range = range;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	@Override
	public String toString() {
		return "SingleAttributeRange [range=" + range + ", times=" + times + "]";
	}	
	
	public static String serializeContext(ChanceClique clique) {
		String serializedObject = "";

		 // serialize the object
		 try {
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     ObjectOutputStream so = new ObjectOutputStream(bo);
		     so.writeObject(clique);
		     so.flush();
//		     serializedObject = bo.toString();
		     serializedObject = new String(Base64.encode(bo.toByteArray()));
		 } catch (Exception e) {
		     System.out.println(e);
		 }
		 
		 return serializedObject;
	}
	
	public static ChanceClique deserializeContext(String serializedObject) {
		ChanceClique obj = null;
		try {
//		     byte b[] = serializedObject.getBytes(); 
		     byte b[] = Base64.decode(serializedObject.toCharArray()); 
		     ByteArrayInputStream bi = new ByteArrayInputStream(b);
		     ObjectInputStream si = new ObjectInputStream(bi);
		     obj = (ChanceClique) si.readObject();
		 } catch (Exception e) {
		     System.out.println(e);
		 }
		
		return obj;
	}
}
