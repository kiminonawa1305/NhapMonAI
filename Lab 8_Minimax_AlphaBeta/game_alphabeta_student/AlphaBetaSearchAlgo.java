package game_alphabeta_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;

		maxValue(node, alpha, beta);
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
		System.out.println("Before -> Node: " + node.getLabel() + "; alpha: " + alpha + " beta: " + beta);
		int v = alpha;
		if(v >= beta) {
			System.out.println("Cut: " + node.getLabel());
			return v;
		}

		Collections.sort(node.getChildren(), Node.LabelComparator);
		for (Node child : node.getChildren()) {
			if (!child.isTerminal()) {
				v = Math.max(v, minValue(child, alpha, beta));
			} else {
				v = Math.max(v, child.getValue());
			}

			alpha =  Math.max(v, alpha);
			System.out.println("After -> Node: " + node.getLabel() + "; alpha: " + alpha + " beta: " + beta);
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
		System.out.println("Before -> Node: " + node.getLabel() + "; alpha: " + alpha + " beta: " + beta);
		int v = beta;
		if(v <= alpha){
			System.out.println("Cut: " + node.getLabel());
			return v;
		}

		Collections.sort(node.getChildren(), Node.LabelComparator);
		for (Node child : node.getChildren()) {
			if (!child.isTerminal()) {
				v = Math.min(v, maxValue(child, alpha, beta));
			} else {
				v = Math.min(v, child.getValue());
			}

			beta = Math.min(v, beta);
			System.out.println("After -> Node: " + node.getLabel() + "; alpha: " + alpha + " beta: " + beta);
		}

		return v;
	}
}
