package puzzle_8.student;

import java.util.ArrayList;

public interface IPuzzleAlgo {
	static ArrayList<String> amountStepByAlgo = new ArrayList<String>();

	public Node execute(Puzzle model);

	public default void addAmountStepByAlgo(String name, int amount, long time){
		amountStepByAlgo.add("Thuật toán " + name + " sẽ đi: " + amount + " bước và tiêu tốn " + time + " ms để hoàn thành ");
	}
}
