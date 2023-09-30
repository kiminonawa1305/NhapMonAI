package agent_matrix;

import agent_matrix.Environment.*;

public class EnvironmentState {
    private LocationState[][] matrixState;
    private Location agentLocation = null;//


    public EnvironmentState(LocationState[][] matrixState) {
        this.matrixState = matrixState;
    }

    public void setAgentLocation(Location location) {
        this.agentLocation = location;
    }


    public Location getAgentLocation() {
        return this.agentLocation;
    }

    public LocationState getLocationState(Location location) {
        return this.matrixState[location.getRow()][location.getCol()];
    }

    public void setLocationState(Location location, LocationState locationState) {
        this.matrixState[location.getRow()][location.getCol()] = locationState;
    }

    public void display() {
        System.out.println("Environment state: \n\t" + this.displayMatrixState());
    }

    public String displayMatrixState() {
        String result = "{ ";
        for (int row = 0; row < this.matrixState.length; row++) {
            for (int col = 0; col < this.matrixState[row].length; col++) {
                if (col == 0) {
                    result += "  " + this.matrixState[row][col] + " ";
                } else {
                    if (row == this.matrixState.length - 1 && col == this.matrixState[row].length - 1) {
                        result += this.matrixState[row][col] + " }";
                    } else {
                        result += this.matrixState[row][col] + " ";
                    }
                }
            }
            result += "\n";
        }

        return result;
    }

    /**
     * check this the Environment is clean
     *
     * @return the EnvironmentState is clean return true else false
     */
    public boolean isDone() {
        for (LocationState[] row : this.matrixState) {
            for (LocationState cell : row) {
                if (cell.equals(LocationState.DIRTY)) {
                    return false;
                }
            }
        }

        return true;
    }
}