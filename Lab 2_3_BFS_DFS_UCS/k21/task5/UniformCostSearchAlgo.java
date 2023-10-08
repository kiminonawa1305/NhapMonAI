package k21.task5;

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

            for (Edge child : current.getChildren()) {
                Node nodeChild = null;
                try {
                    nodeChild = (Node) child.getEnd().clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                child.setEnd(nodeChild);
                nodeChild.setPathCost(current.getPathCost() + child.getWeight());
                System.out.println(child.getEnd().getLabel() + ": (" + current.getLabel() + "=" + current.getPathCost() + "+" + child.getEnd().getLabel() + "=" + child.getWeight() + ")=" + child.getEnd().getPathCost());
                priorityQueue.add(child.getEnd());
                System.out.println(priorityQueue);
                nodeChild.setParent(current);
                nodeChild.setDepth(current.getDepth() + 1);
            }
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
