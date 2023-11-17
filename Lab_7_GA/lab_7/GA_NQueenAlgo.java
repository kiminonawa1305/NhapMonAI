package lab_7;

import java.util.*;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<Node>();
    Random rd = new Random();//initializethe individuals ofthe population

    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    public Node execute() {// Enter your code here
        initPopulation();
        Node result = population.get(0);
        int loop = 0;

        List<Node> new_population = null;
        while (loop < MAX_ITERATIONS) {
            new_population = new ArrayList<Node>();

            for (int i = 0; i < POP_SIZE; i++) {
                Node x = this.getParentByRandomSelection();
                Node y = this.getParentByTournamentSelection();
                Node child = this.reproduce(x, y);

                if (rd.nextDouble() < MUTATION_RATE) mutate(child);

                if(child.getH() == 0){
                    return child;
                }

                new_population.add(child);
            }

            population = new_population;
            loop++;
        }

        Collections.sort(population);
        return population.get(0);
    }

    //Mutate an individualby selecting a random Queen and
    // move it to a random row.
    public void mutate(Node node) {
        // Enter your code here
        /*chọn con đột biến*/
        Queen q = node.getState()[rd.nextInt(Node.N)];

        /*duy chuyển đi qua dòng khác.*/
        int newRow = rd.nextInt(Node.N);
        while (newRow == q.getRow()) {
            newRow = rd.nextInt(Node.N);
        }
        q.setRow(rd.nextInt(Node.N));
    }

    //Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        Node child = new Node();
        child.generateBoard();
        int c = rd.nextInt(Node.N);

        for (int i = 0; i < c; i++) {
            child.setRow(i, x.getRow(i));
        }

        for (int i = c; i < Node.N; i++) {
            child.setRow(i, y.getRow(i));
        }

        return child;
    }

    // Select K individuals from the population at random and
    // select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
        int k = rd.nextInt(2,4);
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < k;) {
            Node child = population.get(rd.nextInt(POP_SIZE));
            if(!nodes.contains(child)) {
                nodes.add(child);
                i++;
            }
        }

        Collections.sort(nodes);
        return nodes.get(0);
    }

    //Select a random parent from the population
    public Node getParentByRandomSelection() {
        return population.get(rd.nextInt(population.size()));
    }

    public static void main(String[] args) {
        GA_NQueenAlgo gaNQueenAlgo = new GA_NQueenAlgo();
        gaNQueenAlgo.execute().displayBoard();
    }
}
