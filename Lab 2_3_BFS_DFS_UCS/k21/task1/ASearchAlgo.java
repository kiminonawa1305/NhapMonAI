package k21.task1;

import java.util.List;

public abstract class ASearchAlgo implements ISearchAlgo {
    protected List<Node> listNodeVisites;

    @Override
    public abstract Node execute(Node root, String goal);

    @Override
    public abstract Node execute(Node root, String start, String goal);
}
