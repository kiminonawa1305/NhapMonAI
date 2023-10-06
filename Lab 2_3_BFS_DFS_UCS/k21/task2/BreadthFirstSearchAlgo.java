package k21.task2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo extends ASearchAlgo {
    private Queue<Node> queue;

    public BreadthFirstSearchAlgo() {
        queue = new LinkedList<>();
        this.listNodeVisited = new ArrayList<>();
    }

    @Override
    public Node execute(Node root, String goal) {
        if (root.getLabel().equals(goal)) {
            return root;
        }

        Node temp = root;
        queue.add(root);
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
        return null;
    }
}
