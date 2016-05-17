import java.util.HashSet;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.alg.CliqueMinimalSeparatorDecomposition;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Graph {
	public void test(){
		UndirectedGraph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
//		// Algorithm example
//		// Add vertices
//		g.addVertex("X1");
//		g.addVertex("X2");
//		g.addVertex("X3");
//		g.addVertex("X4");
//		
//		// Add edges
//		g.addEdge("X1", "X2");
//		g.addEdge("X1", "X4");
//		g.addEdge("X2", "X3");
//		g.addEdge("X4", "X3");
		
//		// Wikipedia chordal graph example
//		// Add vertices
//		g.addVertex("X1");
//		g.addVertex("X2");
//		g.addVertex("X3");
//		g.addVertex("X4");
//		g.addVertex("X5");
//		
//		// Add edges
//		g.addEdge("X1", "X2");
//		g.addEdge("X2", "X3");
//		g.addEdge("X3", "X4");
//		g.addEdge("X4", "X5");
//		g.addEdge("X5", "X1");
		
		// Clique tree algorithm example
		// Add vertices
		g.addVertex("X1");
		g.addVertex("X2");
		g.addVertex("X3");
		g.addVertex("X4");
		g.addVertex("X5");
		g.addVertex("X6");
		g.addVertex("X7");
		
		// Add edges
		g.addEdge("X1", "X5");
		g.addEdge("X1", "X7");
		g.addEdge("X2", "X3");
		g.addEdge("X2", "X6");
		g.addEdge("X3", "X6");
		g.addEdge("X4", "X6");
		g.addEdge("X4", "X7");
		g.addEdge("X5", "X6");
		g.addEdge("X5", "X7");
		g.addEdge("X6", "X7");
		
		
		System.out.println("Graph: " + g.toString());
		
		CliqueMinimalSeparatorDecomposition<String, DefaultEdge> chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
		BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder = new BronKerboschCliqueFinder<>(g); 
		System.out.println("Chordal graph? " + chordalFinder.isChordal());
//		System.out.println("minimal elimination ordering " + chordalFinder.getMeo());
//		System.out.println("Separators: " + chordalFinder.getSeparators());
		System.out.println("Max cliques: " + cliqueFinder.getAllMaximalCliques());
//		System.out.println("Biggest max cliques: " + cliqueFinder.getBiggestMaximalCliques());
		
		
//		 Not necessary with clique tree example
		g = chordalFinder.getMinimalTriangulation();
		chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
		cliqueFinder = new BronKerboschCliqueFinder<>(g);
		
		System.out.println("Chordal Graph: " + g.toString());
		System.out.println("Chordal graph? " + chordalFinder.isChordal());
		System.out.println("minimal elimination ordering " + chordalFinder.getMeo());
		System.out.println("Separators: " + chordalFinder.getSeparators());
		System.out.println("Max cliques: " + cliqueFinder.getAllMaximalCliques());
		System.out.println("Biggest max cliques: " + cliqueFinder.getBiggestMaximalCliques());
		
		SimpleWeightedGraph<String, DefaultWeightedEdge> cliqueGraph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// Iterate over cliques
		for (Set<String> s1 : cliqueFinder.getAllMaximalCliques()) {
//			System.out.println("s1: " + s1);
			for (Set<String> s2 : cliqueFinder.getAllMaximalCliques()) {
				if (s1.equals(s2))
					continue;


				// Calculate intersection between cliques
				Set<String> intersection = new HashSet<String>(s1);
				intersection.retainAll(s2);
								
				if (intersection.size() <= 0) 
					continue;

//				System.out.println("s1: " + s1);
//				System.out.println("s2: " + s2);	
//				
//				System.out.println("Intersection: " + intersection);
//				System.out.println("Intersection length: " + intersection.size());
				
				 
				cliqueGraph.addVertex(s1.toString());
				cliqueGraph.addVertex(s2.toString());
				
				DefaultWeightedEdge e = cliqueGraph.addEdge(s1.toString(), s2.toString()); 
				if (e != null)
					cliqueGraph.setEdgeWeight(e, Integer.MAX_VALUE - intersection.size());
			}
		}
		System.out.println("Maximum-weighted spanning tree: " + cliqueGraph);
		
		PrimMinimumSpanningTree<String, DefaultWeightedEdge> cliqueTree = new PrimMinimumSpanningTree<>(cliqueGraph);
		System.out.println("Edges necessary to get maximum weight clique tree: " + cliqueTree.getMinimumSpanningTreeEdgeSet()); 
		
		// TODO remove all edges except necessary for maximum weight clique tree
//		Set<DefaultWeightedEdge> edges = cliqueTree.getMinimumSpanningTreeEdgeSet();
	}
}
