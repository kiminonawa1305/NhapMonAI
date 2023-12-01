package k21.task3;

import java.util.List;

public abstract class ASearchAlgo implements ISearchAlgo {
    @Override
    public abstract Node execute(Node root, String goal);

    @Override
    public abstract Node execute(Node root, String start, String goal);
}
