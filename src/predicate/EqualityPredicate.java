package predicate;

import java.util.HashMap;

public class EqualityPredicate extends Predicate {
	private int value;
	
	@Override
	public boolean eval(HashMap<String, Integer> values) {
		Integer val = values.get(attribute);
		if (val == null)
			return false;
		
		return val.equals(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EqualityPredicate [value=" + value + ", attribute=" + attribute + "]";
	}

	
}
