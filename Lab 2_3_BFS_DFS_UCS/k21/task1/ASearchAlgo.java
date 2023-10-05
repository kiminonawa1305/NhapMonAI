package k21.task1;

import k21.task2.ISearchAlgo;
import k21.task2.Node;

import java.util.List;

public abstract class ASearchAlgo implements ISearchAlgo {
    protected List<k21.task2.Node> listNodeVisites;

    @Override
    public abstract k21.task2.Node execute(k21.task2.Node root, String goal);

    @Override
    public abstract k21.task2.Node execute(Node root, String start, String goal);
}
