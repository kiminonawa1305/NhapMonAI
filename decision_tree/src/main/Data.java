package main;

import java.util.*;

public class Data {
    private String[] mapLabel;
    private String[][] data;
    private int numberOfRow, numberOfColumn, nextRowToAdd = 0;

    public Data(String[] mapLabel, String[][] data) {
        this.mapLabel = mapLabel;
        this.data = data;
        numberOfColumn = data[0].length;
        numberOfRow = data.length;
    }

    public Data(String[] mapLabel, int numberOfRow, int numberOfColumn) {
        data = new String[numberOfRow][numberOfColumn];
        this.mapLabel = mapLabel;
    }

    public String[] getRowData(int indexRow) {
        return data[indexRow];
    }

    public String[] getColumnData(int indexColumn) {
        String[] result = new String[numberOfRow];
        for (int row = 0; row < numberOfRow; row++) {
            result[row] = data[row][indexColumn];
        }

        return result;
    }

    public double entropy() {
        Map<String, Double> map = new HashMap<String, Double>();
        String[] valuesD = getColumnData(numberOfColumn - 1);
        for (String value : valuesD) {
            if (map.containsKey(value)) map.put(value, map.get(value) + 1);
            else map.put(value, 1.0);
        }
        double result = 0;
        for (Double value : map.values()) {
            result += -(value / numberOfRow) * log2(value / numberOfRow);
        }

        return result;
    }

    public Map<String, Double> count(int indexColCount, String dataCount) {
        Map<String, Double> mapCount = new HashMap<String, Double>();
        for (int row = 0; row < numberOfRow; row++) {
            if (!data[row][indexColCount].equals(dataCount)) continue;
            String key = data[row][numberOfColumn - 1];
            if (mapCount.containsKey(key))
                mapCount.put(key, mapCount.get(key) + 1);
            else mapCount.put(key, 1.0);
        }

        return mapCount;
    }

    public double remainder(Map<String, List<Double>> mapCount) {
        double result = 0;
        Map<String, Double> totals = new HashMap<String, Double>();
        for (Map.Entry<String, List<Double>> entry : mapCount.entrySet()) {
            for (Double value : entry.getValue()) {
                if (totals.containsKey(entry.getKey())) totals.put(entry.getKey(), totals.get(entry.getKey()) + value);
                else totals.put(entry.getKey(), value);
            }
        }
        double total, tempRemainder;

        for (String key : mapCount.keySet()) {
            List<Double> values = mapCount.get(key);
            total = totals.get(key);
            tempRemainder = 0;
            for (Double value : values) tempRemainder += -(value / total) * log2(value / total);
            tempRemainder *= total / numberOfRow;
            result += tempRemainder;
        }

        return result;
    }

    private boolean checkCondition(int indexRow, Map<Integer, String> conditions) {
        for (Map.Entry<Integer, String> entry : conditions.entrySet())
            if (!data[indexRow][entry.getKey()].equals(entry.getValue())) return false;

        return true;
    }

    public static double log2(double n) {
        // calculate log2 N indirectly
        // using log() method
        double result = (Math.log(n) / Math.log(2));
        return result;
    }

    public String[][] getData() {
        return data;
    }

    public int getNumberOfRow() {
        return numberOfRow;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public static void main(String[] args) {
        String[][] data = {
                {"Medium", "Brick", "Blue", "Yes"},
                {"Small", "Wedge", "Red", "No"},
                {"Large", "Wedge", "Red", "No"},
                {"Small", "Sphere", "Red", "Yes"},
                {"Large", "Pillar", "Green", "Yes"},
                {"Large", "Pillar", "Red", "No"},
                {"Large", "Sphere", "Green", "Yes"},
        };

        String[] mapLabel = {"Size", "Shape", "Color", "Choice"};
        Data dataObject = new Data(mapLabel, data);
        System.out.println(dataObject.entropy());
        Map<Integer, Double> remainders = new HashMap<>();
        for (int column = 0; column < dataObject.getNumberOfColumn() - 1; column++) {
            Set<String> keys = new HashSet<>(List.of(dataObject.getColumnData(column)));
            Map<String, List<Double>> mapCounts = new HashMap<>();
            for (String key : keys) {
                Map<String, Double> mapCount = dataObject.count(column, key);
                mapCounts.put(key, mapCount.values().stream().toList());
            }

            remainders.put(column, dataObject.remainder(mapCounts));
        }

        remainders.forEach((key, value) -> {
            System.out.println("Column: " + mapLabel[key] + " có giá trị remainder = " + value);
        });
    }

    public void addRow(String[] row) {
        for (int i = 0; i < numberOfColumn; i++) data[nextRowToAdd][i] = row[i];
        nextRowToAdd++;
    }

    public Data subData(int columnCondition, String dataCondition) {
        String[][] newData = new String[numberOfRow][numberOfColumn];
        int totalRowAdd = 0;
        for (int row = 0; row < numberOfRow; row++) {
            if (this.data[row][columnCondition].equals(dataCondition)) {
                newData[totalRowAdd] = Arrays.copyOf(this.getRowData(row), numberOfColumn);
                totalRowAdd++;
            }
        }

        String[][] result = new String[totalRowAdd][numberOfColumn - 1];
        String[] newMapLabel = new String[numberOfColumn - 1];
        for (int row = 0; row < totalRowAdd; row++) {
            for (int column = 0; column < numberOfColumn; column++) {
                if (column < columnCondition) {
                    result[row][column] = newData[row][column];
                    newMapLabel[column] = mapLabel[column];
                } else if (column > columnCondition) {
                    result[row][column - 1] = newData[row][column];
                    newMapLabel[column - 1] = mapLabel[column];
                }
            }
        }
        return new Data(newMapLabel, result);
    }

    public void display() {
        for (String[] row : data) {
            System.out.println(Arrays.toString(row));
        }
    }

    public String getLabel(int indexChoice) {
        return mapLabel[indexChoice];
    }
}
