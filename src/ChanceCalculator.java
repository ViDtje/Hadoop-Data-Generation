import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import com.esotericsoftware.kryo.util.IdentityMap;

import predicate.Constraint;

public class ChanceCalculator {
	private ArrayList<Constraint> constraints;
	private ArrayList<Set<String>> maxCliques;
	private ArrayList<Integer> domain;
	private int relationCardinality;
	
	public ChanceCalculator(ArrayList<Constraint> constraints, Collection<Set<String>> maxCliques, ArrayList<Integer> domain, int relationCardinality) {
		this.constraints = constraints;
		this.maxCliques = new ArrayList<Set<String>>(maxCliques);
		this.domain = domain;
		this.relationCardinality = relationCardinality;
	}

	
	private ArrayList<HashMap<String, Integer>> getAllPossibilities(ArrayList<String> attributes, ArrayList<Integer> domain, int currentIndex) {
		ArrayList<HashMap<String, Integer>> returnList = new ArrayList<>();
		
		if (currentIndex >= attributes.size()) {
			returnList.add(new HashMap<String, Integer>());
			return returnList;
		}
		
//		returnList = new ArrayList<>();
		for (Integer elem : domain) {
			ArrayList<HashMap<String, Integer>> records = getAllPossibilities(attributes, domain, currentIndex + 1);
			
			// Add elem to every record
			for (HashMap<String, Integer> list : records)
				list.put(attributes.get(currentIndex), elem);
			
			returnList.addAll(records);
		}
		
		return returnList;
	}
	
	public ArrayList<ChanceClique> getChancesForAllCliques() {
		// Loop over cliques
			// per clique lijst van variabelen afgaan om te weten om zo aan de solver in te vullen op 0 of 1 
			// tegelijk ook bijhouden hoeveel formules er zijn?
		System.out.println();
		
		ArrayList<HashMap<String, Integer>>  allVariables = getAllVariables();
//		System.out.println("All variables: " + allVariables);
		ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
		ArrayList<Double> constants = new ArrayList<>();
		
		System.out.println("All constraints: " + constraints);
//		for (Set<String> maxClique : maxCliques) {
		for (int i = 0; i < maxCliques.size() /*- 1*/; i++) {
			Set<String> maxClique = maxCliques.get(i);
			ArrayList<HashMap<String, Integer>> varsForClique = getAllPossibilities(new ArrayList<String>(maxClique), domain, 0);
			ArrayList<Constraint> constraintsForClique = getConstraintsWithAttributes(maxClique);
			
//			System.out.println("posib: " + varsForClique);
//			System.out.println("constraintsForClique: " + constraintsForClique);
//			System.out.println();
			
			// Stel formule 1 op (alle waarden van een clique)
			ArrayList<Double> coefficients = new ArrayList<>();
			for (HashMap<String, Integer> var : allVariables) {
				if (varsForClique.contains(var))
					coefficients.add(1.0);
				else
					coefficients.add(0.0);
				
			}
			matrix.add(coefficients);
			constants.add(1.0);
			
//			System.out.println("possib: " + allPossibilitiesForClique);
//			System.out.println("coefficienten: " + coefficientFormula1);
			
			
			// Stel formule 2 op (eval)
			for (Constraint c : constraintsForClique) {
				coefficients = new ArrayList<>();
				for (HashMap<String, Integer> var : allVariables) {
					if (c.getPredicate().eval(var))
						coefficients.add(1.0);
					else 
						coefficients.add(0.0);
											
				}
				
				// Only add coefficients and constant if they don't already exist
				double constant = (double) c.getCardinality() / (double) relationCardinality;
				// TODO if solver can handle multiples, delete for more efficiency
				if (matrix.contains(coefficients)) {
					if(!constants.get(matrix.indexOf(coefficients)).equals(constant)) {
						matrix.add(coefficients);
						constants.add(constant);
					}
				} else {
					matrix.add(coefficients);
					constants.add(constant);
				}
			}
			
			// Stel formule 3 op (intersection cliques)
			// lus over cliques
			for (int j = i + 1; j < maxCliques.size(); j++) {
				Set<String> secondMaxClique = maxCliques.get(j);
				
				// intersection tussen cliques
				Set<String> intersection = new HashSet<>(maxClique);
				intersection.retainAll(secondMaxClique);
				
				if (intersection.size() <= 0)
					continue;
				
//				System.out.println(intersection);
				
				// Get values for intersection attributes
				ArrayList<HashMap<String, Integer>> varsForIntersection = getAllPossibilities(new ArrayList<String>(intersection), domain, 0);

				// Loop over possible values for intersection attributes
				for (HashMap<String, Integer> varForIntersection : varsForIntersection) {
					ArrayList<HashMap<String,Integer>> varsForClique1 = new ArrayList<>();
					// get all vars for clique1 which match varsForIntersection
					for (HashMap<String, Integer> varForClique : varsForClique) {
						if (varForClique.entrySet().containsAll(varForIntersection.entrySet()))
							varsForClique1.add(varForClique);
					}
					
					ArrayList<HashMap<String,Integer>> varsForClique2 = new ArrayList<>();
					// get all vars for clique2 which match varsForIntersection
					for (HashMap<String, Integer> varForClique : getAllPossibilities(new ArrayList<String>(secondMaxClique), domain, 0)) {
						if (varForClique.entrySet().containsAll(varForIntersection.entrySet()))
							varsForClique2.add(varForClique);
					}
					
//					System.out.println("varforintersection: " + varForIntersection);
//					System.out.println("varsforclique1: " + varsForClique1);
//					System.out.println("varsforclique2: " + varsForClique2);
					
					coefficients = new ArrayList<>();
					for (HashMap<String, Integer> var : allVariables) {
						if (varsForClique1.contains(var))
							coefficients.add(1.0);
						else if (varsForClique2.contains(var))
							coefficients.add(-1.0);
						else 
							coefficients.add(0.0);
					}
					
					matrix.add(coefficients);
					constants.add(0.0);
				}
				
//				System.out.println(varsForIntersection);
			}	
		}
		
		System.out.println("matrix: " + matrix);
		System.out.println("constants: " + constants);
		
		// solve for each var
//		System.out.println(allVariables);
//		System.out.println(solve(twoDimensionalArrayListToArray(matrix), oneDimensionalArrayListToArray(constants)));
		RealVector chanceList = solve(twoDimensionalArrayListToArray(matrix), oneDimensionalArrayListToArray(constants));
		ArrayList<ChanceClique> returnChances = new ArrayList<>();
		for (int i = 0; i < allVariables.size(); i++) {
			ChanceClique c = new ChanceClique();
			c.setVar(allVariables.get(i));
			c.setChance(chanceList.getEntry(i));
			returnChances.add(c);
		}
//		System.out.println(returnChances);
		return returnChances;
		
//		System.out.println("All possib for all cliques: " + allPossibilitiesForAllCliques);
//		System.out.println("All variables: " + allVariables);
//		System.out.println("Number of vars: " + allVariables.size());
	}
	
	private ArrayList<Constraint> getConstraintsWithAttributes(Set<String> maxClique) {
		ArrayList<Constraint> returnList = new ArrayList<>();
		for (Constraint c : constraints) {
			
			Set<String> intersection = new HashSet<String>(c.getAttributes());
			intersection.retainAll(maxClique);
			
			if (intersection.size() <= 0) 
				continue;
			
			returnList.add(c);
		}
		
		return returnList;
	}
	
	private ArrayList<HashMap<String, Integer>> getAllVariables() {
		ArrayList<HashMap<String, Integer>> allPossibilitiesForAllCliques = new ArrayList<>();
		ArrayList<HashMap<String, Integer>>  allVariables = new ArrayList<>();
		for (Set<String> maxClique : maxCliques) {
			// All possibilities
			ArrayList<HashMap<String, Integer>> allPossibilitiesForClique = getAllPossibilities(new ArrayList<String>(maxClique), domain, 0);
			allPossibilitiesForAllCliques.addAll(allPossibilitiesForClique);			
		}
//		System.out.println(allPossibilitiesForAllCliques);
		allVariables = new ArrayList<HashMap<String, Integer>>(new HashSet<HashMap<String, Integer>>(allPossibilitiesForAllCliques));
		return allVariables;
	}
	
	private double[] oneDimensionalArrayListToArray(ArrayList<Double> matrix) {
		return ArrayUtils.toPrimitive(matrix.toArray(new Double[matrix.size()]));
	}
	
	private double[][] twoDimensionalArrayListToArray(ArrayList<ArrayList<Double>> matrix) {
		// arraylist to array
		Double[][] array = new Double[matrix.size()][];
		for (int i = 0; i < matrix.size(); i++) {
		    ArrayList<Double> row = matrix.get(i);
		    array[i] = row.toArray(new Double[row.size()]);
		}
		
		// Double to primitive double
		double[][] doubleArray = new double[array.length][];
		for (int i = 0; i < array.length; i++) 
			doubleArray[i] = ArrayUtils.toPrimitive(array[i]);

		
		return doubleArray;
	}
	
	public RealVector solve(double[][] matrix, double[] constant) {
		RealMatrix coefficients = new Array2DRowRealMatrix(matrix, false);
		DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();
		RealVector constants = new ArrayRealVector(constant, false);
		return solver.solve(constants);
	}

	public void solverTest() {
	//	RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 2, 3, -2 }, { -1, 7, 6 }, { 4, -3, -5 } }, false);
		RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 } }, false);
	//	RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
	//		{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1 } }, false);
		DecompositionSolver solver = new SingularValueDecomposition(coefficients).getSolver();
	//	RealVector constants = new ArrayRealVector(new double[] { 1, -2, 1 }, false);
		RealVector constants = new ArrayRealVector(new double[] { 1, 1, 1 }, false);
		RealVector solution = solver.solve(constants);
//		System.out.println(solution.getEntry(0));
//		System.out.println(solution.getEntry(1));
//		System.out.println(solution.getEntry(2));
//		System.out.println(solution.getEntry(3));
//		System.out.println(solution.getEntry(4));
//		System.out.println(solution.getEntry(5));
	}
}