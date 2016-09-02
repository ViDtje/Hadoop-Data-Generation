package predicate;

import java.util.HashMap;

public class IntervalPredicate extends Predicate {
	private int value1;
	private int value2;

	@Override
	public boolean eval(HashMap<String, Integer> values) {
		Integer val = values.get(attribute);
		if (val == null)
			return false;
		
		return ((value1 <= val) && (val < value2));
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return "IntervalPredicate [value1=" + value1 + ", value2=" + value2 + ", attribute=" + attribute + "]";
	}

	
}
