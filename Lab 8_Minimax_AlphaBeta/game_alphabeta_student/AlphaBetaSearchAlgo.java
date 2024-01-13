package game_alphabeta_student;

import java.util.Collections;
import java.util.Comparator;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;

		node.setValue(maxValue(node, alpha, beta));
	}

	public void executeExtend(Node node, Comparator comparator) {
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;

		node.setValue(maxValue(node, alpha, beta, comparator));
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v
	public int maxValue(Node node, int alpha, int beta) {
		int v = alpha;
		Collections.sort(node.getChildren(), Node.letToRight);
		for (Node child : node.getChildren()) {
			if(alpha >= beta) {
				System.out.println("Cut: " + child.getLabel());
				continue;
			}

			if (!child.isTerminal()) {
				v = Math.max(alpha, minValue(child, alpha, beta));
			} else {
				v = Math.max(alpha, child.getValue());
			}

			alpha =  Math.max(v, alpha); //3
		}

		return v;
	}

	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v
	public int minValue(Node node, int alpha, int beta) {
		int v = beta;

		Collections.sort(node.getChildren(), Node.letToRight);
		for (Node child : node.getChildren()) {
			if(beta <= alpha) {
				System.out.println("Cut: " + child.getLabel());
				continue;
			}

			if (!child.isTerminal()) {
				v = Math.min(beta, maxValue(child, alpha, beta));
			} else {
				v = Math.min(beta, child.getValue());
			}

			beta = Math.min(v, beta);
		}

		return v;
	}

	public int maxValue(Node node, int alpha, int beta, Comparator comparator) {
		int v = alpha;
		Collections.sort(node.getChildren(), comparator);
		for (Node child : node.getChildren()) {
			if(alpha >= beta) {
				System.out.println("Cut: " + child.getLabel());
				continue;
			}

			if (!child.isTerminal()) {
				v = Math.max(alpha, minValue(child, alpha, beta, comparator));
			} else {
				v = Math.max(alpha, child.getValue());
			}

			alpha =  Math.max(v, alpha); //3
		}

		return v;
	}

	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v
	public int minValue(Node node, int alpha, int beta, Comparator comparator) {
		int v = beta;

		Collections.sort(node.getChildren(), comparator);
		for (Node child : node.getChildren()) {
			if(beta <= alpha) {
				System.out.println("Cut: " + child.getLabel());
				continue;
			}

			if (!child.isTerminal()) {
				v = Math.min(beta, maxValue(child, alpha, beta, comparator));
			} else {
				v = Math.min(beta, child.getValue());
			}

			beta = Math.min(v, beta);
		}

		return v;
	}
}
