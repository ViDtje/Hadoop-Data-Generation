package predicate;

import java.util.HashMap;

public class ValueLowerThanAttributePredicate extends Predicate {
	private int value;

	@Override
	public boolean eval(HashMap<String, Integer> values) {
		Integer val = values.get(attribute);
		if (val == null)
			return false;
		
		return value < val;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "valueLowerThanAttributePredicate [value=" + value + ", attribute=" + attribute + "]";
	}
}
