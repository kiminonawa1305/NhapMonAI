package k21.task5_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
    PriorityQueue<Node> priorityQueue;

    @Override
    public Node execute(Node root, String goal) {
        priorityQueue = new PriorityQueue();
        List<Node> explored = new ArrayList<>();
        priorityQueue.add(root);
        Node current;
        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();
            explored.add(current);
            if (current.getLabel().equals(goal)) {
                return current;
            }

            for (Edge child : current.getChildren()) {
                Node nodeChild = child.getEnd();
                double pathCost = current.getPathCost() + child.getWeight();
                if (explored.contains(nodeChild) || pathCost > getPastCost(priorityQueue, nodeChild)) {
                    continue;
                }
                nodeChild.setPathCost(pathCost);
                nodeChild.setParent(current);
                nodeChild.setDepth(current.getDepth() + 1);
                priorityQueue.add(nodeChild);
            }
        }

        return null;
    }

    private double getPastCost(PriorityQueue<Node> priorityQueue, Node node) {
        for (Node n : priorityQueue) {
            if (n.equals(node)) {
                return n.getPathCost();
            }
        }

        return 0;
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
