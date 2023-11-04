package puzzle_8.student;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSearchByF1 implements IPuzzleAlgo{
    @Override
    public Node execute(Puzzle model) {
        String name = "Tìm kiếm A Star Search theo H1";
        int step = 0;
        long start = System.currentTimeMillis(), end = 0;
        Node current = model.getInitialState();
        ArrayList<Node> expected = new ArrayList<Node>();
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);

        System.out.println("=============================" + name + "===================================");

        current.setH(model.computeH1(current));
        frontier.add(current);
        while (!frontier.isEmpty()) {
            current = frontier.poll();
            expected.add(current);
            System.out.println(current);
            if (current.getH() == 0) {
                end = System.currentTimeMillis();
                this.addInfoAlo(name, step, end - start);
                return current;
            }
            step++;
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
