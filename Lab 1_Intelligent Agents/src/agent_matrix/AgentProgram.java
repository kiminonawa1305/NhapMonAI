package agent_matrix;

import java.util.*;

import agent_matrix.Environment.*;

public class AgentProgram {
    private Graph graph;
    private LinkedList<Location> listLocateIsDirty;

    public void convertMatrixStateToGraph(LocationState[][] matrixState) {
        this.graph = new Graph(matrixState);
        scanEnvironment(matrixState);
    }

    public Action execute(Percept p) {// location, status
        if (listLocateIsDirty.contains(p.getAgentLocation())) {
            listLocateIsDirty.remove(p.getAgentLocation());
        }

        if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
            return Environment.SUCK_DIRT;
        }

        return move(p);
    }


    public void scanEnvironment(Environment.LocationState[][] state) {
        listLocateIsDirty = new LinkedList<Location>();
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[row].length; col++) {
                if (state[row][col].equals(LocationState.DIRTY)) {
                    listLocateIsDirty.add(new Location(row, col));
                }
            }
        }
    }

    public Action move(Percept p) {
        int cellSource = graph.getCellFromLocation(p.getAgentLocation().getRow(), p.getAgentLocation().getCol());
        Location locationDest = getLocationHaveMinDistanceToDirty(p.getAgentLocation());
        int cellDest = graph.getCellFromLocation(locationDest.getRow(), locationDest.getCol());
        LinkedList<Integer> path = graph.getPathFromProcedureFloyd(cellSource + 1, cellDest + 1);
        Location moveToLocation = graph.getLocationFromCell(path.get(path.size() - 2));

        if (p.getAgentLocation().getRow() == moveToLocation.getRow()) {
            if (p.getAgentLocation().getCol() + 1 == moveToLocation.getCol()) {
                return Environment.MOVE_RIGHT;
            }

            return Environment.MOVE_LEFT;
        }

        if(p.getAgentLocation().getCol() == moveToLocation.getCol()) {
            if (p.getAgentLocation().getRow() + 1 == moveToLocation.getRow()) {
                return Environment.MOVE_DOWN;
            }

            return Environment.MOVE_UP;
        }



        return Environment.NO_OP_ACTION;
    }

    public Location getLocationHaveMinDistanceToDirty(Location currentLocation){
        Location minLocation = listLocateIsDirty.getFirst();
        for(Location lo : listLocateIsDirty){
            if(graph.distanceCurrentLocationTo(currentLocation, minLocation) > graph.distanceCurrentLocationTo(currentLocation, lo)){
                minLocation = lo;
            };
        }

        return minLocation;
    }

    public boolean isDone() {
        return this.listLocateIsDirty.isEmpty();
    }
}