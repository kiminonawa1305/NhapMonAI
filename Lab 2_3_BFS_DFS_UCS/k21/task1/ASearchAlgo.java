package k21.task1;

public abstract class ASearchAlgo implements ISearchAlgo{
    @Override
    public abstract Node execute(Node root, String goal);

    @Override
    public abstract Node execute(Node root, String start, String goal);
}
