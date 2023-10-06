package k21.task2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo extends ASearchAlgo {
    private Queue<Node> queue;

    public BreadthFirstSearchAlgo() {
    }

    @Override
    public Node execute(Node root, String goal) {
        if (root.getLabel().equals(goal)) {
            return root;
        }

        Node temp;
        queue = new LinkedList<>();
        queue.add(root);
        this.listNodeVisited = new ArrayList<>();
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.getLabel().equals(goal)) {
                return temp;
            }

            listNodeVisited.add(temp);
            for (Node child : temp.getChildrenNodes()) {
                if(!this.listNodeVisited.contains(child) && !this.queue.contains(child)){
                    queue.add(child);
                    child.setParent(temp);
                    child.setDepth(temp.getDepth() + 1);
                }
            }
        }

        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Node nodeStart = execute(root, start);
        nodeStart.setParent(null);
        return execute(nodeStart, goal);
    }
}
