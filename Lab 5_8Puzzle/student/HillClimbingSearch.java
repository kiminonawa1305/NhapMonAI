package puzzle_8.student;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HillClimbingSearch implements IPuzzleAlgo{
    @Override
    public Node execute(Puzzle model) {
        String name = "Tìm kiếm Hill Climbing theo H1";
        int step = 0;
        long start = System.currentTimeMillis(), end = 0;
        Node current = model.getInitialState(), temp;

        System.out.println("=============================" + name + "===================================");

        current.setH(model.computeH1(current));
        int i = 0;
        while (true) {
            System.out.println(current);
            if (current.getH() == 0) {
                end = System.currentTimeMillis();
                this.addInfoAlo(name, step, end - start);
                return current;
            }
            step++;

            temp = null;
            for (Node child : model.getSuccessors(current)) {
                child.setH(model.computeH1(child));
                if (current.getH() > child.getH()) {
                    temp = child;
                    child.setG(current.getG() + 1);
                }
            }

            if(temp == null){
                end = System.currentTimeMillis();
                this.addInfoAlo(name + " đã tìm ra trạng thái tốt nhất là: \n" + current, step, end - start);
                break;
            }

            current = temp;
        }

        return null;
    }
}
