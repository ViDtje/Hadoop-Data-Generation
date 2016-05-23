package predicate;
public class Constraint { 
	private Predicate predicate = null;
	private int cardinality;
	
	
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
	@Override
	public String toString() {
		return "Constraint [predicate=" + predicate + ", cardinality=" + cardinality + "]";
	}
	
	
}
