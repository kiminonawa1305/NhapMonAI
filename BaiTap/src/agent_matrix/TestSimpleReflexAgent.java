package agent_matrix;

import agent_matrix.Environment.*;

public class TestSimpleReflexAgent {
    public static void main(String[] args) {
        Environment env = new Environment(new Environment.LocationState[][]{{LocationState.DIRTY, LocationState.CLEAN}, {LocationState.CLEAN, LocationState.DIRTY}});
        Agent agent = new Agent(new AgentProgram());
        env.addAgent(agent, new Location(0, 0));

        env.step(3);
    }
}
