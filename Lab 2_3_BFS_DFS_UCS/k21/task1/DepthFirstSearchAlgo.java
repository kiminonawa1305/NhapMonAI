package k21.task1;

import k21.task2.Node;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearchAlgo extends ASearchAlgo {
    private Stack<k21.task2.Node> stack;

    public DepthFirstSearchAlgo() {
        stack = new Stack<k21.task2.Node>();
        this.listNodeVisites = new ArrayList<k21.task2.Node>();
    }

    @Override
    public k21.task2.Node execute(k21.task2.Node root, String goal) {
        if (root.getLabel().equals("goal")) {
            return root;
        }

        k21.task2.Node temp = root;
        stack.add(root);
        a:
        while (!stack.isEmpty()) {
            temp = stack.peek();
            listNodeVisites.add(temp);
            for (k21.task2.Node child : temp.getChildrenNodes()) {
                if (!listNodeVisites.contains(child) && !this.stack.contains(child)) {
                    stack.add(child);
                    child.setParent(temp);
                    child.setDepth(temp.getDepth() + 1);
                    continue a;
                }

                if (child.getLabel().equals(goal)) {
                    return child;
                }

            }
            stack.pop();
        }

        return null;
    }

    @Override
    public k21.task2.Node execute(Node root, String start, String goal) {
        return null;
    }
}
