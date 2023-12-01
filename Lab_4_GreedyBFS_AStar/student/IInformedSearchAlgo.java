package lab4.inform.student;

public interface IInformedSearchAlgo {
	public Node execute(Node root, String goal) throws CloneNotSupportedException;

	public Node execute(Node root, String start, String goal) throws CloneNotSupportedException;

}
