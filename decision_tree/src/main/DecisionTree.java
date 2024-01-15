package main;

import java.util.*;

public class DecisionTree {
    private String[] mapLabel;
    String label;
    private Data data;
    private int numberOfColumn, numberOfRow;
    private List<Node> children = new ArrayList<Node>();

    public DecisionTree(String[] mapLabel, int numberOfColumn, int numberOfRow) {
        this.numberOfColumn = numberOfColumn;
        this.numberOfRow = numberOfRow;
        data = new Data(mapLabel, numberOfRow, numberOfColumn);
    }

    public DecisionTree(Data data) {
        this.data = data;
        this.numberOfRow = data.getNumberOfRow();
        this.numberOfColumn = data.getNumberOfColumn();
    }

    public DecisionTree(String[] mapLabel, String[][] data) {
        this.data = new Data(mapLabel, data);
        this.numberOfRow = this.data.getNumberOfRow();
        this.numberOfColumn = this.data.getNumberOfColumn();
    }

    public Map<Integer, Double> getMapRemainder() {
        Map<Integer, Double> mapRemainder = new HashMap<>();
        for (int column = 0; column < data.getNumberOfColumn() - 1; column++) {
            Set<String> keys = new HashSet<>(List.of(data.getColumnData(column)));
            Map<String, List<Double>> mapCounts = new HashMap<>();
            for (String key : keys) {
                Map<String, Double> mapCount = data.count(column, key);
                mapCounts.put(key, mapCount.values().stream().toList());
            }

            mapRemainder.put(column, data.remainder(mapCounts));
        }


        return mapRemainder;
    }

    public Map<Integer, Double> getInformationGain() {
        Map<Integer, Double> mapRemainder = getMapRemainder();
        Map<Integer, Double> mapInformationGain = new HashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> entry : mapRemainder.entrySet())
            mapInformationGain.put(entry.getKey(), data.entropy() - entry.getValue());

        return mapInformationGain;
    }

    public void displayRemainder(Map<Integer, Double> mapRemainder) {
        mapRemainder.forEach((key, value) -> {
            System.out.println("Column: \"" + this.data.getLabel(key) + "\" có giá trị remainder = " + value);
        });
    }

    public void displayInformationGian(Map<Integer, Double> mapInformationGain) {
        mapInformationGain.forEach((key, value) -> {
            System.out.println("Column: \"" + this.data.getLabel(key) + "\" có giá trị Information gain = " + value);
        });
    }

    public double getEntropy() {
        return this.data.entropy();
    }

    public int choiceNodeRoot() {
        Map<Integer, Double> mapRemainder = getMapRemainder();
        int indexChoice = 0;
        for (Map.Entry<Integer, Double> entry : mapRemainder.entrySet()) {
            indexChoice = mapRemainder.get(indexChoice) > entry.getValue() ? entry.getKey() : indexChoice;
        }
        return indexChoice;
    }

    public void addRow(String[] row) {
        data.addRow(row);
    }

    protected void init() {
        int indexChoice = choiceNodeRoot();
        this.label = this.data.getLabel(indexChoice);
        Set<String> keys = new HashSet<>(List.of(data.getColumnData(indexChoice)));
        Node nodeChild;
        for (String key : keys) {
            Map<String, Double> mapCount = data.count(indexChoice, key);
            if (mapCount.size() == 1) nodeChild = new Node(key, String.valueOf(mapCount.keySet().stream().findFirst()));
            else {
                Data dataChild = data.subData(indexChoice, key);
                DecisionTree decisionTreeChild = new DecisionTree(dataChild);
                nodeChild = new Node(key, decisionTreeChild);
            }

            children.add(nodeChild);
        }

        for (Node child : children) child.init();
    }

    public String displayTree(int value) {
        StringBuilder sb = new StringBuilder(label);
        sb.append("\n");
        for (Node node : children) {
            sb.append(node.display(value));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] mapLabel = {"Thời tiết", "Ngày trong tuần", "Thời gian", "Kẹt xe"};
        String[][] data = {
                {"Nắng", "Ngày thường", "Sáng", "Có"},
                {"Mưa", "Ngày thường", "Sáng", "Có"},
                {"Mưa", "Cuối tuần", "Chiều", "Không"},
                {"Nắng", "Cuối tuần", "Sáng", "Không"},
                {"Nắng", "Ngày thường", "Tối", "Không"},
                {"Mưa", "Cuối tuần", "Tối", "Không"},
                {"Nắng", "Ngày thường", "Chiều", "Có"},
                {"Mưa", "Ngày thường", "Tối", "Không"},
                {"Mưa", "Ngày thường", "Chiều", "Không"},
        };

        DecisionTree decisionTree = new DecisionTree(mapLabel, data);
        System.out.println("Entropy: " + decisionTree.getEntropy());
        System.out.println("Remainder: ");
        decisionTree.displayInformationGian(decisionTree.getMapRemainder());
        System.out.println("Information gian: ");
        decisionTree.displayInformationGian(decisionTree.getInformationGain());
        decisionTree.init();
        System.out.println(decisionTree.displayTree(0));
    }
}
