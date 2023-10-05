package agent_matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT"),
            MOVE_RIGHT = new DynamicAction("RIGHT"),
            MOVE_UP = new DynamicAction("UP"),
            MOVE_DOWN = new DynamicAction("DOWN"),
            SUCK_DIRT = new DynamicAction("SUCK"),
            NO_OP_ACTION = NoOpAction.NO_OP;

    public static final double DIRT_RATE = 0.5, WALL_RATE = 0.2;

    public enum LocationState {
        CLEAN, DIRTY, OBSTACLES;
    }

    private EnvironmentState envState;
    private boolean isDone = false;// all squares are CLEAN
    private Agent agent = null;

    public Environment(LocationState[][] matrixState) {
        envState = new EnvironmentState(matrixState);
    }

    public Environment(int row, int col) {
        init(row, col);
    }

    /**
     * Set up random Environment
     */
    public void init(int row, int col) {
        LocationState[][] matrixState = new LocationState[row][col];
        List<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrixState[i][j] = LocationState.CLEAN;
            }
        }


        Random random = new Random();
        int countDirty = (int) (row * col * DIRT_RATE);
        while (countDirty > 0) {
            int r = random.nextInt(0, row);
            int c = random.nextInt(0, col);
            Location location = new Location(r, c);
            if (!locations.contains(location)) {
                locations.add(location);
                matrixState[r][c] = LocationState.DIRTY;
                countDirty--;
            }
        }

        int countObstacles = (int) (row * col * WALL_RATE);
        while (countObstacles > 0) {
            int r = random.nextInt(0, row);
            int c = random.nextInt(0, col);
            Location location = new Location(r, c);
            if (!locations.contains(location)) {
                locations.add(location);
                matrixState[r][c] = LocationState.OBSTACLES;
                countObstacles--;
            }
        }


        envState = new EnvironmentState(matrixState);
    }


    // add an agent into the enviroment
    public boolean addAgent(Agent agent, Location location) {
        if (!this.envState.isObstacleLocation(location)) {
            this.agent = agent;
            this.agent.convertMatrixStateToGraph(this.envState.getMatrixState());
            this.envState.setAgentLocation(location);
            return true;
        }

        return false;
    }

    public boolean addAgent(Agent agent) {
        Random random = new Random();
        while (true) {
            int amountRows = this.envState.getRowCount();
            int amountCols = this.envState.getColumnCount();
            int r = random.nextInt(0, amountRows);
            int c = random.nextInt(0, amountCols);
            Location location = new Location(r, c);
            if (!this.envState.isObstacleLocation(location)) {
                this.agent = agent;
                this.agent.convertMatrixStateToGraph(this.envState.getMatrixState());
                this.envState.setAgentLocation(location);
                return true;
            }
        }
    }

    public EnvironmentState getCurrentState() {
        return this.envState;
    }

    // Update enviroment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        if(action.equals(NO_OP_ACTION)){
            isDone = true;
        }

        if (action.equals(SUCK_DIRT)) {
            this.envState.setLocationState(this.envState.getAgentLocation(), LocationState.CLEAN);
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

    public Action step() {
        envState.display();
        Location agentLocation = this.envState.getAgentLocation();
        Action anAction = agent.execute(getPerceptSeenBy());
        EnvironmentState es = executeAction(anAction);

        System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

        isDone = isDone();

        es.display();

        return anAction;
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

    public EnvironmentState getEnvState() {
        return envState;
    }

    public boolean isDone() {
        return this.agent.isDone();
    }

    public int getScore() {
        return this.agent.getScore();
    }
}
