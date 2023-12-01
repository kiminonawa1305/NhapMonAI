package game_nim_student;

import java.util.*;

public class Node {
    private List<Integer> data = new ArrayList<Integer>();

    public void add(Integer val) {
        this.data.add(val);
    }

    public void addAll(List<Integer> data) {
        this.data.addAll(data);
    }

    // Get children of the current nodes
    public List<Node> getSuccessors() {
        List<Node> result = new ArrayList<Node>();
        Collections.sort(data, DESCOMPARATOR);
        int val = 0;
        int first, second;
        for(Integer v : data){
            if(v > 2){
                val = v;
            }else{
                break;
            }

            first = 1;
            second = val - first;
            while(first < second){
                second = val - first;
                Node nodeChild = new Node();
                ArrayList<Integer> childData = new ArrayList(data);
                childData.remove((Object)val);
                childData.add(first);
                childData.add(second);
                nodeChild.addAll(childData);

                if(!result.contains(nodeChild)) result.add(nodeChild);
                first++;
            }
        }

        return result;
    }

    // Check whether a node is terminal or not
    public boolean isTerminal() {
        for (Integer val : data) {
            if (val > 2) return false;
        }

        return true;
    }

    public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    };

    @Override
    public String toString() {
        Collections.sort(this.data, DESCOMPARATOR);
        return this.data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
