package k21.task4_tree;

import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
    PriorityQueue<Node> priorityQueue;

    @Override
    public Node execute(Node root, String goal) {
        priorityQueue = new PriorityQueue();
        priorityQueue.add(root);
        Node current;
        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();
            if (current.getLabel().equals(goal)) {
                return current;
            }

            duyet(current);
        }

        return null;
    }

    private void duyet(Node current) throws CloneNotSupportedException {
        for (Edge edge : current.getChildren()) {
            Node child = edge.getEnd();
            if (priorityQueue.contains(child)) {
                child = (Node) edge.getEnd().clone();
                edge.setEnd(child);
            }
            child.setParent(current);
            edge.setEnd(child);
            child.setPathCost(current.getPathCost() + edge.getWeight());
            priorityQueue.add(child);
            child.setParent(current);
            child.setDepth(current.getDepth() + 1);
        }
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
