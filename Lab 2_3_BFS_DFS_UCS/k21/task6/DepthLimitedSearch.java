package k21.task6;

import java.util.Stack;

public class DepthLimitedSearch implements ISearchAlgo {
    private int limit;

    public DepthLimitedSearch(int limit) {
        this.limit = limit;
    }

    @Override
    public Node execute(Node root, String goal) {
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        Node current;
        a:
        while (!stack.isEmpty()) {
            current = stack.peek();
            if (current.getLabel().equals(goal)) return current;

            if (current.getDepth() >= limit) {

            }

            for (Node child : current.getChildrenNodes()) {
                if (child.getParent() == null) {
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
        System.out.println("================================================");
        nodeStart.setPathCost(0);
        return execute(nodeStart, goal);
    }
}
