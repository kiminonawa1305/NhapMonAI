package k21.task5_graph;

public class TestSearchAlgo {
    public static void main(String[] args) {
        Node nodeStart = new Node("start");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeGoal = new Node("Goal");
        Node nodeH = new Node("H");
        Node nodeP = new Node("P");
        Node nodeQ = new Node("Q");
        Node nodeR = new Node("R");

        nodeStart.addEdge(nodeD, 3);
        nodeStart.addEdge(nodeE, 9);
        nodeStart.addEdge(nodeP, 1);
        nodeB.addEdge(nodeA, 2);
        nodeC.addEdge(nodeA, 2);
        nodeD.addEdge(nodeB, 1);
        nodeD.addEdge(nodeC, 8);
        nodeD.addEdge(nodeE, 2);
        nodeE.addEdge(nodeR, 9);
        nodeE.addEdge(nodeH, 1);
        nodeF.addEdge(nodeGoal, 5);
        nodeF.addEdge(nodeC, 5);
        nodeR.addEdge(nodeF, 5);
        nodeH.addEdge(nodeP, 4);
        nodeH.addEdge(nodeQ, 4);
        nodeP.addEdge(nodeQ, 15);
        nodeQ.addEdge(nodeR, 3);


        ISearchAlgo uniformCostSearchAlgo = new UniformCostSearchAlgo();
        Node result = uniformCostSearchAlgo.execute(nodeStart,"D", "P");
        System.out.println(NodeUtils.printPath(result));
    }
}
