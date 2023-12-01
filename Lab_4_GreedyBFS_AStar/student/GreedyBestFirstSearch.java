package lab4.inform.student;

import java.util.PriorityQueue;

public class GreedyBestFirstSearch implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) throws CloneNotSupportedException {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>((n1, n2) -> {
            int valueCompare = Double.compare(n1.getH(), n2.getH());
            return valueCompare == 0 ? n1.getLabel().compareTo(n2.getLabel()) : valueCompare;
        });

        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)){
                return current;
            }

            for(Node child : current.getChildrenNodes()) {
                Node nodeChild = child;
                if(frontier.contains(child)){
                    nodeChild = (Node)child.clone();
                }
                nodeChild.setParent(current);
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
