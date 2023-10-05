package agent_matrix;

public class Agent {
    private AgentProgram program;
    private int score;

    public Agent() {
    }

    public Agent(AgentProgram aProgram) {
        program = aProgram;
    }

    /**
     * Trả về hành động khi biết được vị trí hiện tại của con agent và trạng thái của nó
     * @param p
     * @return
     */
    public Action execute(Percept p) {
        if (program != null) {
            Action action = program.execute(p);
            setScore(action);

            return program.execute(p);
        }

        return NoOpAction.NO_OP;
    }

    /**
     * Chuyển đổi ma trận thành đồ thị hổ trợ trong việc tính toán đường đi
     * @param matrixState
     */
    public void convertMatrixStateToGraph(Environment.LocationState[][] matrixState) {
        program.convertMatrixStateToGraph(matrixState);
    }

    /**
     * Kiểm tra xem con agent đã vận hành xong chưa xong chưa
     * @return
     */
    public boolean isDone() {
        return program.isDone();
    }

    public int getScore(){
        return score;
    }

    /**
     * Tính điểm dựa vào action của con agent
     * @param action
     */
    private void setScore(Action action){
        if (action.equals(Environment.SUCK_DIRT)) {
            score += 500;
        } else {
            if (action.equals(Environment.NO_OP_ACTION)) {
                score -= 100;
            } else {
                score -= 10;
            }
        }
    }
}
