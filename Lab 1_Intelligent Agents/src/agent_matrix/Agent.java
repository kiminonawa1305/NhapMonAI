package agent_matrix;

public class Agent {
    private AgentProgram program;

    public Agent() {
    }

    public Agent(AgentProgram aProgram) {
        program = aProgram;
    }

    public Action execute(Percept p) {
        if (program != null) {
            return program.execute(p);
        }

        return NoOpAction.NO_OP;
    }

    public void convertMatrixStateToGraph(Environment.LocationState[][] matrixState) {
        program.convertMatrixStateToGraph(matrixState);
    }

    public boolean isDone(){
        return program.isDone();
    }
}
