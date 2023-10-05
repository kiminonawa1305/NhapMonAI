package k21.task1;

import java.util.Stack;

public class DepthFirstSearchAlgo extends ASearchAlgo {
    private Stack<Node> stack;

    public DepthFirstSearchAlgo() {
        stack = new Stack<Node>();
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
            for (Node child : temp.getChildrenNodes()) {
                if (child.getParent() == null) {
                    stack.add(child);
                    child.setParent(temp);

                    if (child.getLabel().equals(goal)) {
                        return child;
                    }
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
