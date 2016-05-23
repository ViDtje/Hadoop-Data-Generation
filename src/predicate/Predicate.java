package predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
	
	public Set<String> getAttributes(Set<String> attributes) {
		attributes.add(attribute);
		return attributes;
	}
}
