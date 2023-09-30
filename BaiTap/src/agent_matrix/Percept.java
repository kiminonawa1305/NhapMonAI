package agent_matrix;

public class Percept {
	private Location agentLocation;
	private Environment.LocationState state;

	public Percept(Location agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public Location getAgentLocation() {
		return this.agentLocation;
	}
}