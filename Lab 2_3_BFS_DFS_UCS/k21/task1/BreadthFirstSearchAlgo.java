package k21.task1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo extends ASearchAlgo {
    private Queue<Node> queue;

    public BreadthFirstSearchAlgo() {
        queue = new LinkedList<>();
        this.listNodeVisites = new ArrayList<>();
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
            listNodeVisites.add(temp);
            for (Node child : temp.getChildrenNodes()) {
                if(!this.listNodeVisites.contains(child) && !this.queue.contains(child)){
                    queue.add(child);
                    child.setParent(temp);
                    child.setDepth(temp.getDepth() + 1);
                }

                if (child.getLabel().equals(goal)) {
                    return child;
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
