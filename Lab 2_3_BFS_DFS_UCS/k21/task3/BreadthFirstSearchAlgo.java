package k21.task3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo extends ASearchAlgo {
    private Queue<Node> queue;

    public BreadthFirstSearchAlgo() {

    }

    @Override
    public Node execute(Node root, String goal) {
        Node curremt;
        queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            curremt = queue.poll();
            if (curremt.getLabel().equals(goal)) {
                return curremt;
            }

            for (Node child : curremt.getChildrenNodes()) {
                queue.add(child);
                child.setParent(curremt);
                child.setDepth(curremt.getDepth() + 1);
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Node startToRoot = this.execute(root, start);
        startToRoot.setParent(null);
        return this.execute(startToRoot, goal);
    }
}
