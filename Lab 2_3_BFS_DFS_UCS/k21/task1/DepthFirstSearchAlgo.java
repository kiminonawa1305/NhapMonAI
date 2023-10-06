package k21.task1;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearchAlgo extends ASearchAlgo {
    private Stack<Node> stack;

    public DepthFirstSearchAlgo() {
    }

    @Override
    public Node execute(Node root, String goal) {
        if (root.getLabel().equals("goal")) {
            return root;
        }

        Node temp;
        stack = new Stack<Node>();
        stack.add(root);
        this.listNodeVisited = new ArrayList<Node>();
        a:
        while (!stack.isEmpty()) {
            temp = stack.peek();
            if (temp.getLabel().equals(goal)) {
                return temp;
            }

            listNodeVisited.add(temp);
            for (Node child : temp.getChildrenNodes()) {
                if (!listNodeVisited.contains(child) && !this.stack.contains(child)) {
                    stack.add(child);
                    child.setParent(temp);
                    child.setDepth(temp.getDepth() + 1);
                    continue a;
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
