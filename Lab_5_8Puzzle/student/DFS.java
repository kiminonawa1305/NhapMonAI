package puzzle_8.student;

import java.util.*;

public class DFS implements IPuzzleAlgo {
    @Override
    public Node execute(Puzzle model) {
        String name = "Tìm kiếm theo chiều sâu";
        int step = 0;
        long start = System.currentTimeMillis(), end = 0;
        Node current = model.getInitialState();
        Stack<Node> frontier = new Stack<Node>();
        ArrayList<Node> expected = new ArrayList<Node>();

        System.out.println("=============================" + name + "===================================");

        current.setH(model.computeH1(current));
        frontier.add(current);
        int i = 0;
        loop: while (!frontier.isEmpty()) {
            current = frontier.peek();
            expected.add(current);
            System.out.println("\ncurrent\n" + current);
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
                    frontier.addLast(child);
                    continue loop;
                }
            }

            frontier.pop();
        }

        return null;
    }
}
