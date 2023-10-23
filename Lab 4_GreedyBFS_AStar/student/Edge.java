package lab4.inform.student;

public class Edge {
	private Node begin;
	private Node end;
	private double weight;

	public Edge(Node begin, Node end, double weight) {
		super();
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}

	public Edge(Node begin, Node end) {
		super();
		this.begin = begin;
		this.end = end;
		this.weight = 1;
	}

	public Node getBegin() {
		return begin;
	}

	public Node getEnd() {
		return end;
	}

	public double getWeight() {
		return weight;
	}


	public void setEnd(Node end) {
		this.end = end;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Edge(this.begin, this.end, this.weight);
	}
}
