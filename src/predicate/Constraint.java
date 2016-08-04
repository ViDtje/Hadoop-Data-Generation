package predicate;

import java.util.HashSet;
import java.util.Set;

public class Constraint { 
	private Predicate predicate = null;
	private int cardinality;
	private Set<String> attributes = null;
	
	
	public Predicate getPredicate() {
		return predicate;
	}
	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	public int getCardinality() {
		return cardinality;
	}
	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}
	
	public Set<String> getAttributes() {
		if (attributes != null)
			return attributes;
		
		attributes = predicate.getAttributes(new HashSet<String>());
		return attributes;
	}
	
	@Override
	public String toString() {
		return "Constraint [predicate=" + predicate + ", cardinality=" + cardinality + "]";
	}
	
}
