import java.util.ArrayList;
import java.util.Set;

import org.apache.http.impl.io.SocketOutputBuffer;

public class ConstraintGenerator extends UserGenerator {

	@Override
	public String generate() {
		// TODO Auto-generated method stub
		System.out.println("Mapper cliqueOrder: " + mapperContext.getCliqueOrder());
		System.out.println("Mapper chanceClique: " + mapperContext.getChanceCliques());
		// per clique in volgorde genereren
		for (Set<String> clique : mapperContext.getCliqueOrder()) {
			// zoek alle chanceCliques die deze clique bevatten
			ArrayList<ChanceClique> chanceCliques = findChanceClique(clique);
			System.out.println("For clique:" + clique + "the chanceCliques are: " +  chanceCliques);
			System.out.println("TotalChance: "+ calcTotalChance(chanceCliques));
			System.out.println("Generated Clique: " + calculateChanceForClique(chanceCliques));
			// TODO generated chanceCliques bijhouden
			// TODO generated chanceCliques uitschrijven
		}
		
		return "" + (mapperContext.getRecordNr() + mapperContext.getRecordNr());
	}
	
	private ArrayList<ChanceClique> findChanceClique(Set<String> clique) {
		ArrayList<ChanceClique> result = new ArrayList<>();
		for (ChanceClique chanceClique : mapperContext.getChanceCliques()) 
			if (chanceClique.getAttributes().equals(clique))
				result.add(chanceClique);

		return result;
	}
	
	private ChanceClique calculateChanceForClique(ArrayList<ChanceClique> chanceCliques) {
		double totalChance = calcTotalChance(chanceCliques);
		double lowerLimit = 0.0, upperLimit = 0.0;
		double rand = (Math.random() * totalChance);
		System.out.println("chanceCliques in generate: " + chanceCliques);
		System.out.println("Random getal:" + rand);
		for (ChanceClique chanceClique : chanceCliques) {
			double chanceCliqueChance = chanceClique.getChance();
			upperLimit += chanceCliqueChance;
			
			System.out.println("lowerLimit: "+ lowerLimit + ", upperLimit: "+ upperLimit);
			// if chance in bounds
			// TODO werkt enkel met <= upperLimit ?!
			if (lowerLimit <= chanceCliqueChance && chanceCliqueChance <= upperLimit){
				System.out.println("ChanceClique in bounds: " + chanceClique);
				return chanceClique;
			}
			System.out.println("ChanceClique NOT in bounds: " + chanceClique);
			
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
