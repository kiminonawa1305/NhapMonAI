package lab4.inform.student;

public class TestAStar {
	public static void main(String[] args) {
		Node s = new Node("S", 6);
		Node b = new Node("B", 4);
		Node a = new Node("A", 4);
		Node c = new Node("C", 4);
		Node d = new Node("D", 3.5);
		Node e = new Node("E", 1);
		Node f = new Node("F", 1);
		Node g = new Node("G", 0);
		
		s.addEdge(b, 3);
		s.addEdge(a, 2);
		a.addEdge(c, 3);
		b.addEdge(d, 3);
		b.addEdge(c, 1);
		c.addEdge(e, 3);
		c.addEdge(d, 1);
		d.addEdge(f, 2);
		f.addEdge(g, 1);
		e.addEdge(g, 2);

		IInformedSearchAlgo uniformCostSearch = new UniformCostSearch();
		Node resultUniformCostSearch1 = uniformCostSearch.execute(s, g.getLabel());
		System.out.println(NodeUtils.printPath(resultUniformCostSearch1));
		Node resultUniformCostSearch2 = uniformCostSearch.execute(s, a.getLabel(),  g.getLabel());
		System.out.println(NodeUtils.printPath(resultUniformCostSearch2));

		IInformedSearchAlgo aStarSearchAlgo = new AStarSearchAlgo();

		IInformedSearchAlgo greedyBestFirstSearch = new GreedyBestFirstSearch();
	}
}
