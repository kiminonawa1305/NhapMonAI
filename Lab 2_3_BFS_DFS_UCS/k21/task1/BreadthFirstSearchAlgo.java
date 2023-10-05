package k21.task1;

import k21.task2.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo extends ASearchAlgo {
    private Queue<k21.task2.Node> queue;

    public BreadthFirstSearchAlgo() {
        queue = new LinkedList<>();
        this.listNodeVisites = new ArrayList<>();
    }

    @Override
    public k21.task2.Node execute(k21.task2.Node root, String goal) {
        if (root.getLabel().equals(goal)) {
            return root;
        }

        k21.task2.Node temp = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            listNodeVisites.add(temp);
            for (k21.task2.Node child : temp.getChildrenNodes()) {
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
    public k21.task2.Node execute(Node root, String start, String goal) {
        return null;
    }
}
