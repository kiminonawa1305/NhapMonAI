package agent_matrix;

public class Agent {
    private AgentProgram program;
    private int score;

    public Agent() {
    }

    public Agent(AgentProgram aProgram) {
        program = aProgram;
    }

    public Action execute(Percept p) {
        if (program != null) {
            Action action = program.execute(p);
            if (action.equals(Environment.SUCK_DIRT)) {
                score += 500;
            } else {
                if (action.equals(Environment.NO_OP_ACTION)) {
                    score -= 100;
                } else {
                    score -= 10;
                }
            }

            return program.execute(p);
        }

        return NoOpAction.NO_OP;
    }

    public void convertMatrixStateToGraph(Environment.LocationState[][] matrixState) {
        program.convertMatrixStateToGraph(matrixState);
    }

    public boolean isDone() {
        return program.isDone();
    }

    public int getScore(){
        return score;
    }
}
