package game_alphabeta_student;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

    // function MINIMAX-DECISION(state) returns an action
    // inputs: state, current state in game
    // v <- MAX-VALUE(state)
    // return the action in SUCCESSORS(state) with value v
    @Override
    public void execute(Node node) {
        maxValue(node);
    }

    // function MAX-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MIN_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MAX(v, MIN-VALUE(s))
    // return v
    public int maxValue(Node node) {
        int v = Integer.MIN_VALUE;
        // Enter your code here
        for (Node child : node.getChildren()) {
            if (!child.isTerminal()) {
                v = Math.max(v, minValue(child));
            } else {
                v = Math.max(v, child.getValue());
            }
        }

        node.setValue(v);
        return v;
    }

    // function MIN-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MIN(v, MAX-VALUE(s))
    // return v
    public int minValue(Node node) {
        int v = Integer.MAX_VALUE;
        // Enter your code here
        for (Node child : node.getChildren()) {
            if (!child.isTerminal()) {
                v = Math.min(v, maxValue(child));
            } else {
                v = Math.min(v, child.getValue());
            }
        }

        node.setValue(v);
        return v;
    }
}
