package agent_matrix;

public class AgentProgram {

    public Action execute(Percept p) {// location, status
        if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
            return Environment.SUCK_DIRT;
        }

//        if (p.getLocationState().equals(Environment.LOCATION_A)) {
//            return Environment.MOVE_RIGHT;
//        }

        return Environment.MOVE_LEFT;
    }

    public Action move(Percept p){
        Location location = p.getAgentLocation();



        return Environment.MOVE_UP;
    }
}