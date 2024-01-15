package main;

public class Node {
    String label, result;
    DecisionTree children;

    public Node(String value, String result) {
        this.label = value;
        this.result = result;
    }

    public Node(String value, DecisionTree decisionTreeChild) {
        this.label = value;
        this.children = decisionTreeChild;
    }


    public String display(int level) {
        StringBuilder sb = new StringBuilder(space(level));
        int index = 0;
        sb.append("----");
        for(; index < 15 && index < label.length(); index++) sb.append(label.charAt(index));
        for(; index < 15; index++)sb.append(" ");
        sb.append("----");
        if (children != null) sb.append(children.displayTree(level + 1));
        else sb.append(result + "\n");
        return sb.toString();
    }

    public String space(int level) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < level; i++) sb.append("                       |");
        return sb.toString();
    }

    public void init() {
        if (children != null) this.children.init();
    }
}
