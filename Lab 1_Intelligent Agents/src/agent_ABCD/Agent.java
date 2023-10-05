package agent_ABCD;

import java.util.Map;

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

	public String moveTo(String agentLocation, Map<String, Environment.LocationState> state){
		return program.moveTo(agentLocation, state);
	}
}
