package agent_ABCD;

import java.util.Map;
import java.util.Map.Entry;

public class AgentProgram {

    public Action execute(Percept p) {// location, status
        if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
            return Environment.SUCK_DIRT;
        }


        //scan before starting execution
        return Environment.MOVE;
    }

    public String moveTo(String agentLocation, Map<String, Environment.LocationState> state) {
        switch (agentLocation) {
            case Environment.LOCATION_A -> {
                if(state.get(Environment.LOCATION_C).equals(Environment.LocationState.DIRTY)){
                    return Environment.LOCATION_C;
                }

                return Environment.LOCATION_B;
            }
            case Environment.LOCATION_B -> {
                if(state.get(Environment.LOCATION_A).equals(Environment.LocationState.DIRTY)){
                    return Environment.LOCATION_A;
                }

                return Environment.LOCATION_D;
            }
            case Environment.LOCATION_C -> {
                if(state.get(Environment.LOCATION_D).equals(Environment.LocationState.DIRTY)){
                    return Environment.LOCATION_D;
                }

                return Environment.LOCATION_A;
            }
            default -> {
                if(state.get(Environment.LOCATION_B).equals(Environment.LocationState.DIRTY)){
                    return Environment.LOCATION_B;
                }

                return Environment.LOCATION_C;
            }
        }
    }
}