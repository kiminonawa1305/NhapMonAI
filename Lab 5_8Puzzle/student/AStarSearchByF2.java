package puzzle_8.student;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSearchByF2 implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {
        System.out.println("=============================Tìm kiếm A Star Search theo H1===================================");
        int step = 0;
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
        ArrayList<Node> expected = new ArrayList<Node>();
        frontier.add(model.getInitialState());

        model.getInitialState().setH(model.computeH2(model.getInitialState()));
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            expected.add(current);
            System.out.println(current);
            if (current.getH() == 0) {
                this.addAmountStepByAlgo("Tìm kiếm A Star Search theo H2", step);
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
