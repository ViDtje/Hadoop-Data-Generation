package predicate;

import java.util.HashMap;

public abstract class Predicate {
	protected String attribute;
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@Override
	public String toString() {
		return "Predicate [attribute=" + attribute + "]";
	}
	public abstract boolean eval(HashMap<String, Integer> values);
	
}
