package game_nim_student;

public class MinimaxAlgo {
    public void execute(Node node) {
        int v = minValue(node);
        if (v == 0) {
            System.out.println("Min thắng");
        } else {
            System.out.println("Max thắng");
        }
    }

    // function MAX-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MIN_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MAX(v, MIN-VALUE(s))
    // return v
    public int maxValue(Node node) {
        if(node.isTerminal()) return 0;

        for (Node child : node.getSuccessors()) {
            return minValue(child);
        }

        return Integer.MAX_VALUE;
    }

    // function MIN-VALUE(state) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MIN(v, MAX-VALUE(s))
    // return v
    public int minValue(Node node) {
        if(node.isTerminal()) return 1;

        for (Node child : node.getSuccessors()) {
           return maxValue(child);
        }

        return Integer.MIN_VALUE;
    }
}
