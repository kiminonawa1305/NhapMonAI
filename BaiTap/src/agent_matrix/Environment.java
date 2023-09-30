package agent_matrix;

public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT"),
            MOVE_RIGHT = new DynamicAction("RIGHT"),
            MOVE_UP = new DynamicAction("UP"),
            MOVE_DOWN = new DynamicAction("DOWN"),
            SUCK_DIRT = new DynamicAction("SUCK");

    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState envState;
    private boolean isDone = false;// all squares are CLEAN
    private Agent agent = null;

    public Environment(LocationState[][] matrixState) {
        envState = new EnvironmentState(matrixState);
    }


    // add an agent into the enviroment
    public void addAgent(Agent agent, Location location) {
        this.agent = agent;
        this.envState.setAgentLocation(location);
    }

    public EnvironmentState getCurrentState() {
        return this.envState;
    }

    // Update enviroment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        if (action.equals(SUCK_DIRT)) {
            this.envState.setLocationState(this.envState.getAgentLocation(), LocationState.CLEAN);
            return envState;
        }

        Location currentLocation = envState.getAgentLocation();
        if (action.equals(MOVE_LEFT)) {
            this.envState.setAgentLocation(new Location(currentLocation.getRow(), currentLocation.getCol() - 1));
        }

        if (action.equals(MOVE_RIGHT)) {
            this.envState.setAgentLocation(new Location(currentLocation.getRow(), currentLocation.getCol() + 1));
        }

        if (action.equals(MOVE_UP)) {
            this.envState.setAgentLocation(new Location(currentLocation.getRow() - 1, currentLocation.getCol()));
        }

        if (action.equals(MOVE_DOWN)) {
            this.envState.setAgentLocation(new Location(currentLocation.getRow() + 1, currentLocation.getCol()));
        }

        return envState;
    }

    // get percept<AgentLocation, LocationState> at the current location where agent
    // is in.
    public Percept getPerceptSeenBy() {
        return new Percept(this.envState.getAgentLocation(), this.envState.getLocationState(this.envState.getAgentLocation()));
    }

    public void step() {
        envState.display();
        Location agentLocation = this.envState.getAgentLocation();
        Action anAction = agent.execute(getPerceptSeenBy());
        EnvironmentState es = executeAction(anAction);

        System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

//        if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
//                && (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
//            isDone = true;// if both squares are clean, then agent do not need to do any action

        isDone = this.envState.isDone();

        es.display();
    }

    public void step(int n) {
        for (int i = 0; i < n; i++) {
            step();
            System.out.println("-------------------------");
        }
    }

    public void stepUntilDone() {
        int i = 0;

        while (!isDone) {
            System.out.println("step: " + i++);
            step();
        }
    }
}
