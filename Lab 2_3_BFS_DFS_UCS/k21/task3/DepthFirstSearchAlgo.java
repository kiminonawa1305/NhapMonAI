package k21.task3;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearchAlgo extends ASearchAlgo {
    private Stack<Node> stack;

    public DepthFirstSearchAlgo() {
        stack = new Stack<Node>();
    }

    @Override
    public Node execute(Node root, String goal) {
        stack = new Stack<Node>();
        stack.add(root);
        Node current;
        a:
        while (!stack.isEmpty()) {
            current = stack.peek();
            if (current.getLabel().equals(goal)) {
                return current;
            }

            for (Node child : current.getChildrenNodes()) {
                if (child.getParent() == null || !(child.getParent().equals(current))) {
                    stack.add(child);
                    child.setParent(current);
                    child.setDepth(current.getDepth() + 1);
                    continue a;
                }
            }

            stack.pop();
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
