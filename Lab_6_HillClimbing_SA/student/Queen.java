package student;

import java.util.Random;

public class Queen {
    private int row;
    private int column;

    public Queen(int row, int column) {
        super();
        this.row = row;
        this.column = column;
    }

    public void move() {
        // Enter your code here
        /*Chỉ duy chuyển được 1 hướng thôi à*/
        String[] direction = new String[]{"left", "right", "down", "up"};

        Random random = new Random();
        String key = direction[random.nextInt(4)];

        switch (key) {
            case "left" -> {
                --this.column;
            }
            case "right" -> {
                ++this.column;
            }
            case "down" -> {
                ++this.row;
            }
            case "up" -> {
                --this.row;
            }
        }
    }

    // check whether this Queen can attack the given Queen (q)
    public boolean isConflict(Queen q) {
        return this.row == q.row || this.column == q.column ||
                Math.abs(this.row - q.row) == Math.abs(this.column - q.column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
