package k21.task4_tree;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    private String label;
    private Node parent; // for printing the path from the start node to goal node
    private double pathCost;// from the root node to this node
    private int depth;// used for compute the depth of a node in a tree search
    private List<Edge> children = new ArrayList<Edge>();

    public Node(String label) {
        this.label = label;
    }

    public Node(String label, int h) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Edge> getChildren() {
        return children;
    }

    public List<Node> getChildrenNodes() {
        List<Node> result = new ArrayList<Node>();
        for (Edge edge : this.children) {
            result.add(edge.getEnd());
        }
        return result;
    }

    // an edge is generated using this node and "that" with the given cost
    public void addEdge(Node that, double cost) {
        Edge edge = new Edge(this, that, cost);
        this.children.add(edge);
    }

    // an edge is generated using this node and "that" with the given cost
    public void addEdge(Node that) {
        Edge edge = new Edge(this, that);
        this.children.add(edge);
    }

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        return true;
    }

    public void setChildren(List<Edge> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return this.label + "_" + (this.parent == null ? "" : this.parent.getLabel()) + " " + this.pathCost;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.pathCost, o.pathCost) != 0 ? Double.compare(this.pathCost, o.pathCost) : this.label.compareTo(o.label);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Node clone = new Node(this.getLabel());
        List<Edge> cloneListChildren = new ArrayList<Edge>();
        for (Edge edge : this.getChildren()) {
            Edge cloneEdge = (Edge) edge.clone();
            if (edge.getEnd().equals(this)) {
                cloneEdge.setEnd(clone);
            }

            if (edge.getBegin().equals(this)) {
                cloneEdge.setBegin(clone);
            }

            cloneListChildren.add(edge);
        }

        clone.setChildren(cloneListChildren);
        return clone;
    }
}
