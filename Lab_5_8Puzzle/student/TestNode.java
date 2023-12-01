package puzzle_8.student;


public class TestNode {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("txt/PuzzleMap.txt", "txt/PuzzleGoalState.txt");

		GreedyBestFirstSearchByH1 greedyBestFirstSearchByH1 = new GreedyBestFirstSearchByH1();
		greedyBestFirstSearchByH1.execute(p);
		GreedyBestFirstSearchByH2 greedyBestFirstSearchByH2 = new GreedyBestFirstSearchByH2();
		greedyBestFirstSearchByH2.execute(p);
		AStarSearchByF1 aStarSearchByF1 = new AStarSearchByF1();
		aStarSearchByF1.execute(p);
		AStarSearchByF2 aStarSearchByF2 = new AStarSearchByF2();
		aStarSearchByF2.execute(p);
		BFS bfs = new BFS();
		bfs.execute(p);
		DFS dfs = new DFS();
		dfs.execute(p);
		HillClimbingSearch hillClimbingSearch = new HillClimbingSearch();
		hillClimbingSearch.execute(p);

		for(String info : IPuzzleAlgo.infoAlo){
			System.out.println(info);
		}
	}

}
