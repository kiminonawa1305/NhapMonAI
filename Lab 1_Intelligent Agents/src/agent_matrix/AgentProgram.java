package agent_matrix;

import java.util.*;

import agent_matrix.Environment.*;

public class AgentProgram {
    private Graph graph;
    private LinkedList<Location> listLocateIsDirty;

    /**
     * Chuyển ma trận thành đồ thị
     * @param matrixState
     */
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


    /**
     * Quét ma trận sẽ thực thi để có thể tìm ra các vị trí đang bị dơ
     * @param state
     */
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

    /**
     * Thực hiện hành động duy chuyển của con agent.
     * -Lọc các vị trí đang bị bẩn trong listLocateIsDirty và dựa vào khoảng cách vị trí hiện tại
     * đên vị trí đang bị bẩn đó mà tìm ra được vị trí bẫn gần nhất vị trí hiện tại đang đứng
     * @param p
     * @return
     */
    public Action move(Percept p) {
        // Lấy ra vị trí agent đang đúng và chuyển vị trí đó thành thứ tự ô trong đồ thị
        int cellSource = graph.getCellFromLocation(p.getAgentLocation().getRow(), p.getAgentLocation().getCol());
        // Lấy ra vị trí bẩn gần với vị trí agent đang đứng nhất.
        Location locationDest = getLocationHaveMinDistanceToDirty(p.getAgentLocation());
        //Chuyển vị trí gần nhất đó thành thứ tự ô trong đồ thị
        int cellDest = graph.getCellFromLocation(locationDest.getRow(), locationDest.getCol());
        //Lấy đường đi trong đồ thị đã thực hiện thuật toán tìm đường đi ngắn nhất để lấy ra đường đi ngắn nhất từ vị trí hiện tại
        // đến vị trí bị bẩn gần nhất
        LinkedList<Integer> path = graph.getPathFromProcedureFloyd(cellSource + 1, cellDest + 1);
        //Lấy ra vị trí tiếp theo sẽ duy chuyển sau khi đã biết đường đi và chuyển nó thành vị trí trong ma trận
        Location moveToLocation = graph.getLocationFromCell(path.get(path.size() - 2));

        //Căn cứ vào vị trí đã tìm ra mà tìm ra hành động hợp lý
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

    /**
     * Tìm ra vị trí bẩn gần với vị trí hiện tại nhất
     * @param currentLocation
     * @return
     */
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