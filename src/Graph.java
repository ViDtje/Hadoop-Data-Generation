import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.alg.CliqueMinimalSeparatorDecomposition;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Graph {
	public void test(){
		UndirectedGraph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
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
		
		// Add vertices
		g.addVertex("X1");
		g.addVertex("X2");
		g.addVertex("X3");
		g.addVertex("X4");
		g.addVertex("X5");
		
		// Add edges
		g.addEdge("X1", "X2");
		g.addEdge("X2", "X3");
		g.addEdge("X3", "X4");
		g.addEdge("X4", "X5");
		g.addEdge("X5", "X1");
		
		System.out.println(g.toString());
		
		CliqueMinimalSeparatorDecomposition<String, DefaultEdge> chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
		BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder = new BronKerboschCliqueFinder<>(g); 
		System.out.println("Chordal graph? " + chordalFinder.isChordal());
		System.out.println("minimal elimination ordering " + chordalFinder.getMeo());
		System.out.println("Separators: " + chordalFinder.getSeparators());
		System.out.println("Max cliques: " + cliqueFinder.getAllMaximalCliques());
		System.out.println("Biggest max cliques: " + cliqueFinder.getBiggestMaximalCliques());
		
		g = chordalFinder.getMinimalTriangulation();
		chordalFinder = new CliqueMinimalSeparatorDecomposition<>(g);
		cliqueFinder = new BronKerboschCliqueFinder<>(g);
		
		System.out.println(g.toString());
		System.out.println("Chordal graph? " + chordalFinder.isChordal());
		System.out.println("minimal elimination ordering " + chordalFinder.getMeo());
		System.out.println("Separators: " + chordalFinder.getSeparators());
		System.out.println("Max cliques: " + cliqueFinder.getAllMaximalCliques());
		System.out.println("Biggest max cliques: " + cliqueFinder.getBiggestMaximalCliques());
	}
}
