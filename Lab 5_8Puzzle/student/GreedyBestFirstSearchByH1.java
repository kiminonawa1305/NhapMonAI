package puzzle_8.student;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchByH1 implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {
        System.out.println("=============================Tìm kiếm Greedy Best First theo H1===================================");
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
        ArrayList<Node> expected = new ArrayList<Node>();
        frontier.add(model.getInitialState());

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            expected.add(current);
            System.out.println(current);
            if (model.computeH1(current) == 0) return current;

            for (Node child : model.getSuccessors(current)) {
                if (!frontier.contains(child) && !expected.contains(child)) {
                    child.setH(model.computeH1(child));
                    child.setG(current.getG() + 1);
                    frontier.add(child);
                }
            }
        }

        return null;
    }
}
