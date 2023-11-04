package puzzle_8.student;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchByH2 implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {
        String name = "Tìm kiếm Greedy Best First theo H2";
        int step = 0;
        long start = System.currentTimeMillis(), end = 0;
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
        ArrayList<Node> expected = new ArrayList<Node>();

        System.out.println("=============================" + name + "===================================");

        model.getInitialState().setH(model.computeH2(model.getInitialState()));
        frontier.add(model.getInitialState());
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            expected.add(current);
            System.out.println(current);
            if (current.getH() == 0) {
                end = System.currentTimeMillis();
                this.addAmountStepByAlgo(name, step, end - start);
                return current;
            }
            step++;

            for (Node child : model.getSuccessors(current)) {
                if (!frontier.contains(child) && !expected.contains(child)) {
                    child.setH(model.computeH2(child));
                    child.setG(current.getG() + 1);
                    frontier.add(child);
                }
            }
        }

        return null;
    }
}
