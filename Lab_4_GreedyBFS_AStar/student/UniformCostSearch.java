package lab4.inform.student;

import java.util.PriorityQueue;

public class UniformCostSearch implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) throws CloneNotSupportedException {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>((n1, n2) -> {
            int valueCompare = Double.compare(n1.getG(), n2.getG());
            return valueCompare == 0 ? n1.getLabel().compareTo(n2.getLabel()) : valueCompare;
        });

        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) {
                return current;
            }

            for (Edge edge : current.getChildren()) {
                Node child = edge.getEnd();
                if(frontier.contains(child)){
                    child = (Node)edge.getEnd().clone();
                }
                child.setParent(current);
                child.setG(current.getG() + edge.getWeight());
                frontier.add(child);
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) throws CloneNotSupportedException {
        Node nodeStart = execute(root, start);
        nodeStart.setParent(null);
        return execute(nodeStart, goal);
    }
}
