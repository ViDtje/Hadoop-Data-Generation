import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConstraintGenerator extends UserGenerator {
	private Set<ChanceClique> chosenChanceCliques = new HashSet<>();
	
	@Override
	public String generate() {
//		System.out.println("BEGIN MAPPER");
		chosenChanceCliques = new HashSet<>();
//		System.out.println("Mapper cliqueOrder: " + mapperContext.getCliqueOrder());
//		System.out.println("Mapper chanceClique: " + mapperContext.getChanceCliques());
		// per clique in volgorde genereren
		for (Set<String> clique : mapperContext.getCliqueOrder()) {
			// zoek alle chanceCliques die deze clique bevatten
			ArrayList<ChanceClique> chanceCliques = findChanceClique(clique);
//			System.out.println("For clique: " + clique + "the chanceCliques are: " +  chanceCliques);
			chanceCliques = removeChosenChanceCliques(chanceCliques);
//			System.out.println("For clique: " + clique + "the remaining chanceCliques are: " +  chanceCliques);
			ChanceClique chosenClique = chooseClique(chanceCliques);
//			System.out.println("Generated Clique: " + chosenClique);
			chosenChanceCliques.add(chosenClique);
			
		}
//		System.out.println("END MAPPER\n");
		
		// generated chanceCliques uitschrijven in volgorde
		String output = new String();
		for (String attribute : mapperContext.getAttributes()){
			// Make 1 hashmap where we can sample out
			HashMap<String, Integer> attributes = findChosenAttributes();
			
			
			if (output.isEmpty())
				output = "" + attributes.get(attribute);
			else
				output += " " + attributes.get(attribute);
		}
		return output;
	}
	
	private ArrayList<ChanceClique> findChanceClique(Set<String> clique) {
		ArrayList<ChanceClique> result = new ArrayList<>();
		for (ChanceClique chanceClique : mapperContext.getChanceCliques()) 
			if (chanceClique.getAttributes().equals(clique)) 
					result.add(chanceClique);
			
		return result;
	}
	
	// Iterates over chosenChanceCliques and removes the chanceCliques that have the same attribute but a different value
	private ArrayList<ChanceClique> removeChosenChanceCliques(ArrayList<ChanceClique> chanceCliques) {
		ArrayList<ChanceClique> result = new ArrayList(chanceCliques);
		HashMap<String, Integer> chosenAttributes = findChosenAttributes();
//		System.out.println("chosenAttributes: " + chosenAttributes);
		for (ChanceClique chanceClique : chanceCliques) {	
			for (Map.Entry<String, Integer> entry : chosenAttributes.entrySet()) {
				String chosenAttribute = entry.getKey();
				Integer chosenValue = entry.getValue();
				Integer chanceCliqueValue = chanceClique.getVar().get(chosenAttribute);
				
				// wanneer de key er in zit, maar de value verschillend is, verwijderen
				if (chanceCliqueValue != null && chanceCliqueValue != chosenValue)
					result.remove(chanceClique);
			}
		}
		
		return result;
	}
	
	private HashMap<String, Integer> findChosenAttributes() {
		HashMap<String, Integer> result = new HashMap<>();
		for (ChanceClique chosenChanceClique : chosenChanceCliques) {
			// Should work because it only has 1 unique key
			result.putAll(chosenChanceClique.getVar());
		}
		
		return result;
	}
	
	private ChanceClique chooseClique(ArrayList<ChanceClique> chanceCliques) {
		double totalChance = calcTotalChance(chanceCliques);
//		System.out.println("Total chance: " + totalChance);
		double lowerLimit = 0.0, upperLimit = 0.0;
		double rand = (Math.random() * totalChance);
//		System.out.println("chanceCliques in generate: " + chanceCliques);
//		System.out.println("Random getal:" + rand);
		for (ChanceClique chanceClique : chanceCliques) {
			double chanceCliqueChance = chanceClique.getChance();
			upperLimit += chanceCliqueChance;
			
//			System.out.println("lowerLimit: "+ lowerLimit + ", upperLimit: "+ upperLimit);
			// if chance in bounds
			if (lowerLimit <= rand && rand < upperLimit){
//				System.out.println("ChanceClique in bounds: " + chanceClique);
				return chanceClique;
			}
//			System.out.println("ChanceClique NOT in bounds: " + chanceClique);
			lowerLimit += chanceCliqueChance;
		}
		// TODO throw exception
		return null;
	}
	
	private double calcTotalChance(ArrayList<ChanceClique> chanceCliques) {
		double result = 0.0;
		for (ChanceClique chanceClique : chanceCliques) 
			result += chanceClique.getChance();
		
		return result;
	}
}
