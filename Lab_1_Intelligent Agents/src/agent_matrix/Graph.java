package agent_matrix;

import java.util.*;

public class Graph {
    private int graph[][], path[][], w[][];
    private static int numVertex, numColInMatrixState;

    public Graph(Environment.LocationState[][] state) {
        this.init(state);
        this.procedureFloyd();
    }

    public void init(Environment.LocationState[][] state) {
        numVertex = state.length * state[0].length;
        numColInMatrixState = state[0].length;
        graph = new int[numVertex][numVertex];
        w = new int[numVertex][numVertex];
        path = new int[numVertex][numVertex];

        //Set default distances
        for (int row = 0; row < numVertex; row++) {
            for (int col = 0; col < numVertex; col++) {
                w[row][col] = Integer.MAX_VALUE;
            }
        }

        //create graph
        for (int soure = 0; soure < graph.length; soure++) {
            int row = soure / numColInMatrixState,
                    col = soure - (row * numColInMatrixState),
                    up = row - 1, down = row + 1, left = col - 1, right = col + 1;


            if (state[row][col] != Environment.LocationState.OBSTACLES) {
                int dest = 0;

                //MOVE DOWN
                if (down < state.length && state[down][col] != Environment.LocationState.OBSTACLES) {
                    dest = getCellFromLocation(down, col);
                    graph[soure][dest] = 1;
                    graph[dest][soure] = 1;
                    w[soure][dest] = 1;
                    w[dest][soure] = 1;
                    path[soure][dest] = dest + 1;
                    path[dest][soure] = soure + 1;
                }

                //MOVE UP
                if (up >= 0 && state[up][col] != Environment.LocationState.OBSTACLES) {
                    dest = getCellFromLocation(up, col);
                    graph[soure][dest] = 1;
                    graph[dest][soure] = 1;
                    w[soure][dest] = 1;
                    w[dest][soure] = 1;
                    path[soure][dest] = dest + 1;
                    path[dest][soure] = soure + 1;
                }

                //MOVE LEFT
                if (left >= 0 && state[row][left] != Environment.LocationState.OBSTACLES) {
                    dest = getCellFromLocation(row, left);
                    graph[soure][dest] = 1;
                    graph[dest][soure] = 1;
                    w[soure][dest] = 1;
                    w[dest][soure] = 1;
                    path[soure][dest] = dest + 1;
                    path[dest][soure] = soure + 1;
                }

                //MOVE RIGHT
                if (right < numColInMatrixState && state[row][right] != Environment.LocationState.OBSTACLES) {
                    dest = getCellFromLocation(row, right);
                    graph[soure][dest] = 1;
                    graph[dest][soure] = 1;
                    w[soure][dest] = 1;
                    w[dest][soure] = 1;
                    path[soure][dest] = dest + 1;
                    path[dest][soure] = soure + 1;
                }
            }
        }

        this.graph = graph;
    }

    public void display(int[][] object) {
        int countRow = 1;
        for (int[] row : object) {
            System.out.println("row: " + countRow + " ->" + Arrays.toString(row));
            countRow++;
        }
    }

    public void procedureFloyd() {
//        display(path);
//        System.out.println("================================================");
        for (int index = 0; index < numVertex; index++) {
            for (int row = 0; row < numVertex; row++) {
                if (row != index && w[row][index] != Integer.MAX_VALUE) {
                    for (int col = 0; col < numVertex; col++) {
                        if (col != index && w[index][col] != Integer.MAX_VALUE
                                && w[row][index] + w[index][col] < w[row][col]) {
                            w[row][col] = w[row][index] + w[index][col];
                            path[row][col] = index + 1;
                        }
                    }
                }
            }
        }

//        display(path);
//        System.out.println("================================================");
//        display(w);
    }

    public LinkedList<Integer> getPathFromProcedureFloyd(int src, int dest) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        while (dest != src) {
            linkedList.add(dest);
            dest = path[dest - 1][src - 1];
        }
        linkedList.add(src);

        return linkedList;
    }


    public void dijkstra(int src, int dest) {
        int saveSrc = src;
        List<Integer> r = new ArrayList<>();
        int[] l = new int[numVertex];
        String[] p = new String[numVertex];
        for (int i = 0; i < numVertex; i++) {
            r.add(i + 1);
            p[i] = "-";
            l[i] = Integer.MAX_VALUE;
        }

        l[src - 1] = 0;

        while (src != dest) {
            int kSrc = Integer.MAX_VALUE;
            for (int i = 0; i < r.size(); i++) {
                if (l[r.get(i) - 1] < kSrc) {
                    src = r.get(i);
                    kSrc = l[r.get(i) - 1];
                }
            }

            // xóa đỉnh đã duyệt
            r.remove((Object) (src));
            for (int i = 0; i < r.size(); i++) {
                if (w[src - 1][r.get(i) - 1] != Integer.MAX_VALUE
                        && l[src - 1] + w[src - 1][r.get(i) - 1] < l[r.get(i) - 1]) {
                    l[r.get(i) - 1] = l[src - 1] + w[src - 1][r.get(i) - 1];
                    p[r.get(i) - 1] = src + "";
                }
            }
        }

        printPath(p, l, saveSrc, dest);
    }

    public void printPath(String[] p, int l[], int src, int dest) {
        String re = dest + "";
        int sumPath = l[dest - 1];
        a:
        while (true) {
            for (int i = 0; i < numVertex; i++) {
                re += "->" + p[dest - 1];

                if (Integer.valueOf(p[dest - 1]) == src) {
                    System.out.println(re + " : " + sumPath);
                    break a;
                }

                dest = Integer.valueOf(p[dest - 1]);
            }
        }
    }

    public int distanceCurrentLocationTo(Location current, Location to) {
        int cellSrc = getCellFromLocation(current.getRow(), current.getCol());
        int cellDst = getCellFromLocation(to.getRow(), to.getCol());
        return w[cellSrc][cellDst];
    }

    /**
     * Convert to cell from index row and index column
     *
     * @param row
     * @param col
     * @return index cell from 0 to row*col
     */
    public int getCellFromLocation(int row, int col) {
        return row * numColInMatrixState + col;
    }

    public Location getLocationFromCell(int cell) {
        int row = (cell - 1) / numColInMatrixState,
                col = (cell - 1) - (row * numColInMatrixState);

        return new Location(row, col);
    }

    public int[][] getGraph() {
        return graph;
    }

    public int[][] getPath() {
        return path;
    }

    public int[][] getW() {
        return w;
    }
}
