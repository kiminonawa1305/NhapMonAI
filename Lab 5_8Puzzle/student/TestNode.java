package puzzle_8.student;


public class TestNode {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("txt/PuzzleMap.txt", "txt/PuzzleGoalState.txt");

//		Node initialState = p.getInitialState();
//		Node tmp = new Node(initialState);
//		GreedyBestFirstSearchByH1 greedyBestFirstSearchByH1 = new GreedyBestFirstSearchByH1();
//		greedyBestFirstSearchByH1.execute(p);
//		GreedyBestFirstSearchByH2 greedyBestFirstSearchByH2 = new GreedyBestFirstSearchByH2();
//		greedyBestFirstSearchByH2.execute(p);
//		AStarSearchByF1 aStarSearchByF1 = new AStarSearchByF1();
//		aStarSearchByF1.execute(p);
//		AStarSearchByF2 aStarSearchByF2 = new AStarSearchByF2();
//		aStarSearchByF2.execute(p);
//		BFS bfs = new BFS();
//		bfs.execute(p);
		DFS dfs = new DFS();
		dfs.execute(p);

		for(String info : IPuzzleAlgo.infoAlo){
			System.out.println(info);
		}
//		Node goalState = p.getGoalState();
//		System.out.println(p.getInitialState());
//		System.out.println("H: "+ initialState.getH());
//		System.out.println(initialState);
//		System.out.println(p.computeH1(initialState));
//		List<Node> listChild = p.getSuccessors(initialState);
//		for(int i = 0; i < listChild.size() - 1; i++) {
//			System.out.println("Check hash: " + (listChild.get(i).hashCode() == listChild.get(i + 1).hashCode()));
//		}
//		System.out.println(Arrays.toString(initialState.getWhiteTilePosition()));
//		System.out.println(p.getGoalState());
//		Node re = p.moveWhiteTile(initialState, 'r');
//
//		System.out.println(re);
//		System.out.println(re.getH());
//		System.out.println(initialState);
//		System.out.println(Arrays.toString(re.getWhiteTilePosition()));
//		System.out.println(p.computeH(init, goal));

//		 System.out.println(p.getInitialState());
//		 System.out.println(p.getGoalState());
//
//		 List<Node> children = p.getSuccessors(initialState);
//		 System.out.println("Size: "+children.size());
//		 for (Node child : children) {
//		 	System.out.println(child.getH()+" "+p.computeH(child) );
//		 }
	}
}
