package agent_ABCD;

public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT"),
            MOVE_RIGHT = new DynamicAction("RIGHT"),
            MOVE_UP = new DynamicAction("UP"),
            MOVE_DOWN = new DynamicAction("DOWN "),
            MOVE = new DynamicAction("MOVE"),
            SUCK_DIRT = new DynamicAction("SUCK");
    public static final String LOCATION_A = "A",
            LOCATION_B = "B",
            LOCATION_C = "C",
            LOCATION_D = "D";

    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState envState;
    private boolean isDone = false;// all squares are CLEAN
    private Agent agent = null;

    public Environment(LocationState locAState, LocationState locBState,
                       LocationState locCState, LocationState locDState) {
        envState = new EnvironmentState(locAState, locBState, locCState, locDState);
    }


    // add an agent into the enviroment
    public void addAgent(Agent agent, String location) {
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
            System.out.println("Agent Loc.: " + this.envState.getAgentLocation() + "\tAction: " + action);
            return envState;
        }

        String moveTO = this.agent.moveTo(this.envState.getAgentLocation(), this.envState.getMapEnvironmentState());
        System.out.println("Agent Loc.: " + this.envState.getAgentLocation() + "\tAction: MOVE TO " + moveTO);
        this.envState.setAgentLocation(moveTO);

        return envState;
    }

    // get percept<AgentLocation, LocationState> at the current location where agent
    // is in.
    public Percept getPerceptSeenBy() {
        return new Percept(this.envState.getAgentLocation(), this.envState.getLocationState(this.envState.getAgentLocation()));
    }

    public void step() {
        envState.display();
        String agentLocation = this.envState.getAgentLocation();
        Action anAction = agent.execute(getPerceptSeenBy());
        executeAction(anAction);
//        EnvironmentState es = executeAction(anAction);

//        if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
//                && (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
//            isDone = true;// if both squares are clean, then agent do not need to do any action
        isDone = envState.isDone();

        envState.display();
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
            System.out.println("-------------------------");
        }
    }
}
