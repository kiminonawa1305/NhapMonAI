package k21.task1;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearchAlgo extends ASearchAlgo {
    private Stack<Node> stack;

    public DepthFirstSearchAlgo() {
        stack = new Stack<Node>();
        this.listNodeVisites = new ArrayList<Node>();
    }

    @Override
    public Node execute(Node root, String goal) {
        if (root.getLabel().equals("goal")) {
            return root;
        }

        Node temp = root;
        stack.add(root);
        a:
        while (!stack.isEmpty()) {
            temp = stack.peek();
            listNodeVisites.add(temp);
            for (Node child : temp.getChildrenNodes()) {
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
    public Node execute(Node root, String start, String goal) {
        return null;
    }
}
