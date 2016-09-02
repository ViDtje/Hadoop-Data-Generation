import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.alg.CliqueMinimalSeparatorDecomposition;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import predicate.Constraint;

public class Graph {
	private UndirectedGraph<String, DefaultEdge> graph;
	private ArrayList<Constraint> constraints;
	private ArrayList<ChanceClique> chanceCliques;
	private ArrayList<Set<String>> cliqueOrder = new ArrayList<>();
	private ArrayList<String> attributes;
	private ArrayList<Integer> domain = new ArrayList<>();
	
	int relationCardinality;
	
	public Graph(ArrayList<String> attributes, int nrOfRecords , ArrayList<Integer> domain) {
		this.attributes = attributes;
		relationCardinality = nrOfRecords;
		this.domain = domain;
	}
	
	public void parseConstraints(ArrayList<Constraint> constraints) {
		this.constraints = constraints;
		
		makeGraph();
		graphAlgorithms();
		
		UndirectedGraph chordalGraph = getChordalGraph(graph);
		ChanceCalculator calc = new ChanceCalculator(constraints, getMaximalCliques(chordalGraph), domain, relationCardinality);
		chanceCliques = calc.getChancesForAllCliques();
	}
	
	public ArrayList<ChanceClique> getChanceCliques() {
		return chanceCliques;
	}
	
	public ArrayList<Set<String>> getCliqueOrder() {
		return cliqueOrder;
	}
	
	private void makeGraph() {
		graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		for (String attr : attributes)
			graph.addVertex(attr);
		
		
		for (Constraint c : constraints) {
			Set<String> attrs = c.getPredicate().getAttributes(new HashSet<String>());
			
			// Add vertices
			for (String s : attrs) 
				graph.addVertex(s);
			
			// Add edges
			for (String s1 : attrs) {
				for (String s2 : attrs) {
					if (!s1.equals(s2))
						graph.addEdge(s1, s2);
				}
			}
		}
	}
	
	private void graphAlgorithms(){	
		UndirectedGraph chordalGraph = getChordalGraph(graph);
		SimpleWeightedGraph<String, DefaultWeightedEdge> weightedCliqueIntersectionGraph = getWeightedCliqueIntersectionGraph(getMaximalCliques(chordalGraph));
		SimpleWeightedGraph cliqueTree = getMaximumWeightSpanningTree(weightedCliqueIntersectionGraph);
		
//		System.out.println("Graph: " + graph);
//		System.out.println("chordalGraph: " + chordalGraph);
		Collection<Set<String>> maxCliques = getMaximalCliques(chordalGraph);
//		System.out.println("maxCliques: " + maxCliques);
		if (maxCliques.size() <= 1) {
//			System.out.println("maxCliques.size() <= 1");
			cliqueOrder.addAll(maxCliques);
			return;
		}
		
//		if (cliqueTree.)
		
//		System.out.println("Cliques: " + maxCliques);
		System.out.println("weightedCliqueIntersectionGraph: " + weightedCliqueIntersectionGraph);
		System.out.println("cliqueTree: " + cliqueTree);
		
		BreadthFirstIterator<Set<String>, DefaultWeightedEdge> order = new BreadthFirstIterator<>(cliqueTree);
		while (order.hasNext()) {
			Set<String> next = order.next();
			cliqueOrder.add(next);
//			System.out.println(next);
		}
		
		if (cliqueOrder.isEmpty())
			cliqueOrder.addAll(maxCliques);
//		System.out.println("cliqueOrder: " + cliqueOrder);
		
	}
	
	private UndirectedGraph getChordalGraph(UndirectedGraph g) {
		CliqueMinimalSeparatorDecomposition<String, DefaultEdge> chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
		
		if (chordalFinder.isChordal())
			return g;
		
		return chordalFinder.getMinimalTriangulation();
	}
	
	private Collection getMaximalCliques(UndirectedGraph<String, DefaultEdge> g) {
		BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder = new BronKerboschCliqueFinder<>(g); 
		return cliqueFinder.getAllMaximalCliques();
	}
	
	private SimpleWeightedGraph getWeightedCliqueIntersectionGraph(Collection<Set<String>> maxCliques) {
		SimpleWeightedGraph<Set<String>, DefaultWeightedEdge> weightedCliqueIntersectionGraph = new SimpleWeightedGraph<Set<String>, DefaultWeightedEdge>(DefaultWeightedEdge.class);
//		Collection<Set> maxCliques = getMaximalCliques(g);
		
		// Iterate over cliques to make weighted clique intersection graph
		for (Set s1 : maxCliques) {
			for (Set s2 : maxCliques) {
				if (s1.equals(s2))
					continue;

				// Calculate intersection between cliques
				Set<String> intersection = new HashSet<String>(s1);
				intersection.retainAll(s2);
								
				if (intersection.size() <= 0) 
					continue;
				
				weightedCliqueIntersectionGraph.addVertex(s1); // TODO eerst cliques toevoegen voor efficientie
				weightedCliqueIntersectionGraph.addVertex(s2);
				
				DefaultWeightedEdge e = weightedCliqueIntersectionGraph.addEdge(s1, s2); 
				if (e != null) // e == null if it has already been added
					weightedCliqueIntersectionGraph.setEdgeWeight(e,  - intersection.size()); // need to get maximal spanning tree
			}
		}
	
		return weightedCliqueIntersectionGraph;
	}
	
	private SimpleWeightedGraph getMaximumWeightSpanningTree(SimpleWeightedGraph weightedCliqueIntersectionGraph) {
		PrimMinimumSpanningTree<Set<String>, DefaultWeightedEdge> primMinimumSpanningTree = new PrimMinimumSpanningTree<>(weightedCliqueIntersectionGraph);
		
		// remove all edges except necessary for maximum weight clique tree
		SimpleWeightedGraph<Set<String>, DefaultWeightedEdge> cliqueTree = (SimpleWeightedGraph<Set<String>, DefaultWeightedEdge>) weightedCliqueIntersectionGraph.clone();
		LinkedList<DefaultWeightedEdge> copy = new LinkedList<>();
		for (DefaultWeightedEdge e : cliqueTree.edgeSet())  
			copy.add(e);// TODO knopen toevoegen aan nieuwe graaf ipv edges verwijderen -> efficienter
		cliqueTree.removeAllEdges(copy);
		
		// add edges necessary for maximum weight clique tree
		for (DefaultWeightedEdge e : primMinimumSpanningTree.getMinimumSpanningTreeEdgeSet()) 
			cliqueTree.addEdge(cliqueTree.getEdgeSource(e), cliqueTree.getEdgeTarget(e), e);
		
		return cliqueTree;
	}
}
