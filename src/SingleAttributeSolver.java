import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import predicate.Constraint;
import predicate.IntervalPredicate;

public class SingleAttributeSolver {

	public ArrayList<SingleAttributeRange> solve(ArrayList<Integer> domain, ArrayList<Constraint> constraints, int nrOfRecords) {
		System.out.println("Single Attribute algorithm");
		
		// make Set with limits from domain and constraints
		SortedSet<Double> limits = new TreeSet();
		limits.add((double) domain.get(0));
		limits.add((double)domain.get(domain.size() - 1) + 1);
		
		// get all limits
		for (Constraint c : constraints) {
			limits.add((double) ((IntervalPredicate) c.getPredicate()).getValue1());
			limits.add((double) ((IntervalPredicate) c.getPredicate()).getValue2());
		}
		System.out.println("Limits: " + limits);
		ArrayList<Double> limitsList = new ArrayList<>(limits);
		
		// make ranges from the limits
		ArrayList<IntRange> ranges = new ArrayList<>();
		for (int i = 0; i < limitsList.size() - 1; i++) 
			ranges.add(new IntRange(limitsList.get(i), limitsList.get(i + 1) - 1));
		
		System.out.println(ranges);
		
		// make equations
		ArrayList<Double> coeff = new ArrayList<>();
		for (int i = 0; i < ranges.size(); i++)
			coeff.add(1.0);
        LinearObjectiveFunction f = new LinearObjectiveFunction(ArrayUtils.toPrimitive(coeff.toArray(new Double[coeff.size()])), 0);	        
        ArrayList<LinearConstraint> linearConstraints = new ArrayList<>();
		linearConstraints.add(new LinearConstraint(ArrayUtils.toPrimitive(coeff.toArray(new Double[coeff.size()])), Relationship.EQ, nrOfRecords));
		
//					System.out.println("Coeff: " + coeff + " = " + nrOfRecords);
		
		for (Constraint c : constraints) {
			Integer constraintLower = ((IntervalPredicate) c.getPredicate()).getValue1();
			Integer constraintUpper = ((IntervalPredicate) c.getPredicate()).getValue2() - 1;
			IntRange constraintRange = new IntRange(constraintLower, constraintUpper);
			coeff = new ArrayList<>();
			for (IntRange range : ranges) {
				if (range.overlapsRange(constraintRange))
					coeff.add(1.0);
				else
					coeff.add(0.0);
			}
//						System.out.println("Coeff: " + coeff + " = " + c.getCardinality());
			linearConstraints.add(new LinearConstraint(ArrayUtils.toPrimitive(coeff.toArray(new Double[coeff.size()])), Relationship.EQ, c.getCardinality()));
		}
        
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(100), f, new LinearConstraintSet(linearConstraints), GoalType.MAXIMIZE, new NonNegativeConstraint(true));
        
//			        System.out.println(Arrays.toString(solution.getPoint()));
        
        ArrayList<SingleAttributeRange> singleAttributeRanges = new ArrayList<>();
        for (int i = 0; i < ranges.size(); i++) 
        	singleAttributeRanges.add(new SingleAttributeRange(ranges.get(i), (int) solution.getPoint()[i]));
        
        return singleAttributeRanges;
	}
}
