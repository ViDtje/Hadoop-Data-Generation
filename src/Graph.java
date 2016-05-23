import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.alg.CliqueMinimalSeparatorDecomposition;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.alg.TarjanLowestCommonAncestor;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import antlr.ConstraintGrammarLexer;
import antlr.ConstraintGrammarParser;
import antlr.ConstraintGrammarVisitor;
import antlr.InputToConstraintVisitor;
import predicate.Constraint;

public class Graph {
	private UndirectedGraph<String, DefaultEdge> graph;
	private ArrayList<Constraint> constraints;
	
	public void parseConstraints() {
//		ArrayList<Constraint> constraints = makeConstraints();
//		System.out.println(constraints);
//		graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
//		
//		for (Constraint c : constraints) {
//			graph.addVertex(c.getPredicates().get(0).getAttribute());
//			graph.addVertex(c.getPredicates().get(1).getAttribute());
//			graph.addEdge(c.getPredicates().get(0).getAttribute(), c.getPredicates().get(1).getAttribute());
//		}
//		
//		graphAlgorithms();
		
		String inputString = "|A1=0&&A2=0|=5 \n |A2=0&&A3=0|=5 \n |A3=0&&A4=0|=5\n";
		
		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(inputString); 
		
		// create a lexer that feeds off of input CharStream
		ConstraintGrammarLexer lexer = new ConstraintGrammarLexer(input); 
		
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		
		// create a parser that feeds off the tokens buffer
		ConstraintGrammarParser parser = new ConstraintGrammarParser(tokens);
		
		ParseTree tree = parser.constraints(); // begin parsing at init rule
		
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
		
		
		InputToConstraintVisitor vis = new InputToConstraintVisitor();
		vis.visit(tree);
//		System.out.println(vis.getConstraints());
		constraints = vis.getConstraints();
		
		makeGraph();
	}
	
	public void makeGraph() {
		graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
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
			
		System.out.println(graph);
	}
	
	public void graphAlgorithms(){	
		UndirectedGraph chordalGraph = getChordalGraph(graph);
		SimpleWeightedGraph<String, DefaultWeightedEdge> weightedCliqueIntersectionGraph = getWeightedCliqueIntersectionGraph(graph);
		SimpleWeightedGraph cliqueTree = getMaximumWeightSpanningTree(weightedCliqueIntersectionGraph);
		
		System.out.println("Graph: " + graph);
		System.out.println("chordalGraph: " + chordalGraph);
		System.out.println("weightedCliqueIntersectionGraph: " + weightedCliqueIntersectionGraph);
		System.out.println("cliqueTree: " + cliqueTree);
		
		
//		System.out.println("Graph: " + g.toString());
//		
//		CliqueMinimalSeparatorDecomposition<String, DefaultEdge> chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
//		BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder = new BronKerboschCliqueFinder<>(g); 
//		System.out.println("Chordal graph? " + chordalFinder.isChordal());
//		System.out.println("Max cliques: " + cliqueFinder.getAllMaximalCliques());
//		
//		
//		g = chordalFinder.getMinimalTriangulation();
//		chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
//		cliqueFinder = new BronKerboschCliqueFinder<>(g);
//		
//		System.out.println("Chordal Graph: " + g.toString());
//		System.out.println("Chordal graph? " + chordalFinder.isChordal());
//		System.out.println("Separators: " + chordalFinder.getSeparators());
//		System.out.println("Max cliques: " + cliqueFinder.getAllMaximalCliques());
//		
//		SimpleWeightedGraph<String, DefaultWeightedEdge> weightedCliqueIntersectionGraph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
//		
//		// Iterate over cliques to make weighted clique intersection graph
//		for (Set<String> s1 : cliqueFinder.getAllMaximalCliques()) {
//			for (Set<String> s2 : cliqueFinder.getAllMaximalCliques()) {
//				if (s1.equals(s2))
//					continue;
//
//				// Calculate intersection between cliques
//				Set<String> intersection = new HashSet<String>(s1);
//				intersection.retainAll(s2);
//								
//				if (intersection.size() <= 0) 
//					continue;
//				
//				 
//				weightedCliqueIntersectionGraph.addVertex(s1.toString());
//				weightedCliqueIntersectionGraph.addVertex(s2.toString());
//				
//				DefaultWeightedEdge e = weightedCliqueIntersectionGraph.addEdge(s1.toString(), s2.toString()); 
//				if (e != null)
//					weightedCliqueIntersectionGraph.setEdgeWeight(e, - intersection.size());
//			}
//		}
//		
//		System.out.println("Maximum-weighted spanning tree: " + weightedCliqueIntersectionGraph);
//		
//		PrimMinimumSpanningTree<String, DefaultWeightedEdge> primMinimumSpanningTree = new PrimMinimumSpanningTree<>(weightedCliqueIntersectionGraph);
//		System.out.println("Edges necessary to get maximum weight clique tree: " + primMinimumSpanningTree.getMinimumSpanningTreeEdgeSet());
//		
//		// remove all edges except necessary for maximum weight clique tree
//		SimpleWeightedGraph<String, DefaultWeightedEdge> cliqueTree = (SimpleWeightedGraph<String, DefaultWeightedEdge>) weightedCliqueIntersectionGraph.clone();
//		LinkedList<DefaultWeightedEdge> copy = new LinkedList<>();
//		for (DefaultWeightedEdge e : cliqueTree.edgeSet()) 
//			copy.add(e);
//		cliqueTree.removeAllEdges(copy);
//		
//		// add edges necessary for maximum weight clique tree
//	
//		for (DefaultWeightedEdge e : primMinimumSpanningTree.getMinimumSpanningTreeEdgeSet()) 
//			cliqueTree.addEdge(cliqueTree.getEdgeSource(e), cliqueTree.getEdgeTarget(e), e);
//
//		System.out.println("CliqueTree: " + cliqueTree);
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
	
	private SimpleWeightedGraph getWeightedCliqueIntersectionGraph(UndirectedGraph<String, DefaultEdge> g) {
		SimpleWeightedGraph<String, DefaultWeightedEdge> weightedCliqueIntersectionGraph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Collection<Set> maxCliques = getMaximalCliques(g);
		
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
				
				weightedCliqueIntersectionGraph.addVertex(s1.toString()); // TODO eerst cliques toevoegen
				weightedCliqueIntersectionGraph.addVertex(s2.toString());
				
				DefaultWeightedEdge e = weightedCliqueIntersectionGraph.addEdge(s1.toString(), s2.toString()); 
				if (e != null) // e == null if it has already been added
					weightedCliqueIntersectionGraph.setEdgeWeight(e,  - intersection.size()); // need to get maximal spanning tree
			}
		}
	
		return weightedCliqueIntersectionGraph;
	}
	
	private SimpleWeightedGraph getMaximumWeightSpanningTree(SimpleWeightedGraph weightedCliqueIntersectionGraph) {
		PrimMinimumSpanningTree<String, DefaultWeightedEdge> primMinimumSpanningTree = new PrimMinimumSpanningTree<>(weightedCliqueIntersectionGraph);
		
		// remove all edges except necessary for maximum weight clique tree
		SimpleWeightedGraph<String, DefaultWeightedEdge> cliqueTree = (SimpleWeightedGraph<String, DefaultWeightedEdge>) weightedCliqueIntersectionGraph.clone();
		LinkedList<DefaultWeightedEdge> copy = new LinkedList<>();
		for (DefaultWeightedEdge e : cliqueTree.edgeSet())  
			copy.add(e);// TODO knopen toevoegen aan nieuwe graaf ipv 
		cliqueTree.removeAllEdges(copy);
		
		// add edges necessary for maximum weight clique tree
		for (DefaultWeightedEdge e : primMinimumSpanningTree.getMinimumSpanningTreeEdgeSet()) 
			cliqueTree.addEdge(cliqueTree.getEdgeSource(e), cliqueTree.getEdgeTarget(e), e);
		
		return cliqueTree;
	}
}
