import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

import org.apache.directory.api.util.Base64;

public class ChanceClique implements java.io.Serializable {
	private HashMap<String, Integer> var = new HashMap<>();
	private double chance;
	
	
	public HashMap<String, Integer> getVar() {
		return var;
	}
	public void setVar(HashMap<String, Integer> var) {
		this.var = var;
	}
	public double getChance() {
		return chance;
	}
	public void setChance(double chance) {
		this.chance = chance;
	}
	
	public Set<String> getAttributes() {
		return var.keySet();
	}
	
	@Override
	public String toString() {
		return "ChanceClique [var=" + var + ", chance=" + chance + "]";
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
