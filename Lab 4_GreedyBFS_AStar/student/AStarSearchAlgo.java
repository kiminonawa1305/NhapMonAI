package lab4.inform.student;

import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) throws CloneNotSupportedException {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>((n1, n2) -> {
            int valueCompare = Double.compare(n1.getF(), n2.getF());
            return valueCompare == 0 ? n1.getLabel().compareTo(n2.getLabel()) : valueCompare;
        });
        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            System.out.println("Node explanded: " + current.getLabel());
            if(current.getLabel().equals(goal)){
                return current;
            }

            System.out.print("Priority queue: ");
            for (Edge edge : current.getChildren()) {
                Node child = edge.getEnd();
                if(frontier.contains(child)){
                    child = (Node)edge.getEnd().clone();
                }
                child.setParent(current);
                child.setG(current.getG() + edge.getWeight());
                child.setF(child.getH() + child.getG());
                System.out.print(child.getLabel() + "=" + child.getG() + "+" + child.getH() + "=" + child.getF() + "; ");

                frontier.add(child);
            }
            System.out.println("\n" + frontier);
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
