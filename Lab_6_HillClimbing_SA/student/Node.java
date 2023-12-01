package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		state = new Queen[N];
		generateBoard();
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for(int current = 0; current < N - 1; current++) {
			for(int other = current + 1; other < N; other++) {
				if(state[current].isConflict(state[other])){
					heuristic++;
					break;
				}
			}
		}
		return heuristic;
	}

	/*all status by node current*/
	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();

		for(int i = 0; i < N; i++) {
			Node child = new Node(this);

			//Mỗi trạng thái chỉ có 8 node con tương ứng có 8 cách duy chuyển của từng quân cờ.
			child.state[i].move();

			if(child.state[i].getColumn() < 0){
				child.state[i].setColumn(N - 1);
			}

			if(child.state[i].getRow() < 0){
				child.state[i].setRow(N - 1);
			}

			if(child.state[i].getColumn() == 8){
				child.state[i].setColumn(0);
			}

			if(child.state[i].getRow() == 8){
				child.state[i].setRow(0);
			}

			result.add(child);
		}

		// Enter your code here
		return result;
	}

	/*Tiềm kiếm SA sẽ dùng phương thức này. còn HillClimbing không dùng nó*/
	public Node selectNextRandomCandidate() {
		Random random = new Random();
		return this.generateAllCandidates().get(random.nextInt(N));
	}

	public Node getBestCandidate(){
		Node result = this;

		for(Node child : this.generateAllCandidates()){
			if(result.getH() > child.getH()){
				result = child;
			}
		}

		return result;
	};

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = i + 1;
		}

		System.out.println("H: " + this.getH());
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0) {
					System.out.print(board[i][j] + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Node node = new Node();
		node.displayBoard();
		System.out.println(node.getH());
	}
}
