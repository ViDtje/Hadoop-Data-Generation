package predicate;

import java.util.HashMap;

public class DisjunctionPredicate extends Predicate {
	private Predicate predicate1 = null;
	private Predicate predicate2 = null;

	@Override
	public boolean eval(HashMap<String, Integer> values) {
		return predicate1.eval(values) || predicate2.eval(values);
	}

	public Predicate getPredicate1() {
		return predicate1;
	}

	public void setPredicate1(Predicate predicate1) {
		this.predicate1 = predicate1;
	}

	public Predicate getPredicate2() {
		return predicate2;
	}

	public void setPredicate2(Predicate predicate2) {
		this.predicate2 = predicate2;
	}

	@Override
	public String toString() {
		return "DisjunctionPredicate [predicate1=" + predicate1 + ", predicate2=" + predicate2 + "]";
	}
}
