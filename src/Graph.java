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

public class Graph {
	UndirectedGraph<String, DefaultEdge> graph;
	
	public void constraintTests() {
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
		
		String inputString = "| A = 0 &&0<B|=1 \n | A = 0 &&0>B|=1\n\n |A=0&&0<B<10||C=2|=2 \n\n\n |A=0||0<B<10&&C=2|=3\n";
		
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
		System.out.println(vis.constraints);
	}
	
//	private ArrayList<Constraint> makeConstraints() {
//		ArrayList<Constraint> constraints = new ArrayList<>();
//		
//		Predicate p1 = new Predicate();
//		p1.setAttribute("A1");
//		p1.setValue(0);
//		Predicate p2 = new Predicate();
//		p2.setAttribute("A2");
//		p2.setValue(0);
//		Constraint c = new Constraint();
//		c.setCardinality(5);
//		c.getPredicates().add(p1);
//		c.getPredicates().add(p2);
//		constraints.add(c);
//		
//		p1 = new Predicate();
//		p1.setAttribute("A2");
//		p1.setValue(0);
//		p2 = new Predicate();
//		p2.setAttribute("A3");
//		p2.setValue(0);
//		c = new Constraint();
//		c.setCardinality(5);
//		c.getPredicates().add(p1);
//		c.getPredicates().add(p2);
//		constraints.add(c);
//		
//		p1 = new Predicate();
//		p1.setAttribute("A3");
//		p1.setValue(0);
//		p2 = new Predicate();
//		p2.setAttribute("A4");
//		p2.setValue(0);
//		c = new Constraint();
//		c.setCardinality(5);
//		c.getPredicates().add(p1);
//		c.getPredicates().add(p2);
//		constraints.add(c);
//		
//		return constraints;
//	}
	
	public void graphAlgorithms(){
		
		
		
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
//		graph.addVertex("X1");
//		graph.addVertex("X2");
//		graph.addVertex("X3");
//		graph.addVertex("X4");
//		graph.addVertex("X5");
//		graph.addVertex("X6");
//		graph.addVertex("X7");
//		
//		// Add edges
//		graph.addEdge("X1", "X5");
//		graph.addEdge("X1", "X7");
//		graph.addEdge("X2", "X3");
//		graph.addEdge("X2", "X6");
//		graph.addEdge("X3", "X6");
//		graph.addEdge("X4", "X6");
//		graph.addEdge("X4", "X7");
//		graph.addEdge("X5", "X6");
//		graph.addEdge("X5", "X7");
//		graph.addEdge("X6", "X7");
		
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
