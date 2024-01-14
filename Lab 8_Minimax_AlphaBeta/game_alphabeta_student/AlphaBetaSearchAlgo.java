package game_alphabeta_student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

    // function ALPHA-BETA-SEARCH(state) returns an action
    // inputs: state, current state in game
    // v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
    // return the action in SUCCESSORS(state) with value v
    @Override
    public void execute(Node node) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        node.setValue(maxValue(node, alpha, beta));
    }

    public void executeExtend(Node root, Comparator comparator) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        if (checkSortLeftToRight(root)) {
            root.setValue(maxValue(root, alpha, beta, comparator));
        } else {
            throw new Exception("Lỗi sắp xếp thứ tự các nốt!\nVui lòng thêm các nốt theo tứ tự tăng dần từ trái sang phải theo bảng chữ cái!");
        }
    }

    public Node executeExtend() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node("A");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        insertNode(queue, reader, root.getLabel());
        String ask = "";
        while (true) {
            System.out.println("Kiểm tra từ trái qua phải?\ny: Từ trái qua qua.\nn: Từ phải qua trái.\nquit: Thoát!");
            ask = reader.readLine();
            if (ask == null || ask.toLowerCase().equals("quit")) break;
            if (ask.toLowerCase().equals("y"))
                root.setValue(maxValue(root, Integer.MIN_VALUE, Integer.MAX_VALUE, Node.letToRight));
            if (ask.toLowerCase().equals("n"))
                root.setValue(maxValue(root, Integer.MIN_VALUE, Integer.MAX_VALUE, Node.rightToLeft));

        }

        reader.close();
        return root;
    }

    public void insertNode(Queue<Node> queue, BufferedReader reader, String nextLabel) throws IOException {
        int nodeChildren = 0;
        while (!queue.isEmpty()) {
            Node root = queue.poll();
            while (true) {
                System.out.print("Nhập số node con của node " + root.getLabel() + ": ");
                try {
                    nodeChildren = Integer.parseInt(reader.readLine());
                    break;
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            for (int i = 0; i < nodeChildren; i++) {
                String childLabel = (root.getLabel() + (root.getChildren().size() + 1));
                while (true) {
                    System.out.print("Node " + childLabel + " có phải là node lá không? y/n: ");
                    String ask = reader.readLine();
                    if (ask.toLowerCase().equals("n")) {
                        nextLabel = (char) (nextLabel.charAt(0) + 1) + "";
                        Node child = new Node(nextLabel);
                        queue.add(child);
                        root.addChild(child);
                        break;
                    }

                    int value = 0;
                    if (ask.toLowerCase().equals("y")) {
                        while (true) {
                            System.out.print("Vui lòng nhập giá trị node " + childLabel + ": ");
                            try {
                                value = Integer.parseInt(reader.readLine());
                                break;
                            } catch (NumberFormatException e) {
                                continue;
                            }
                        }

                        root.addChild(new Node(childLabel, value));
                        break;
                    }
                }
            }
        }
    }

    public boolean
    checkSortLeftToRight(Node node) {
        List<Node> root = new ArrayList<Node>(node.getChildren());
        final boolean[] result = {true};
        Comparator comparatorCheck = new Comparator<Node>() {
            boolean check = result[0];

            public void merge() {
                result[0] &= check;
            }

            public void update() {
                check = result[0];
            }

            @Override
            public int compare(Node o1, Node o2) {
                update();
                int result = o1.getLabel().compareTo(o2.getLabel());
                check &= result == 1 ? true : false;
                merge();
                if (!o1.isTerminal()) {
                    check &= checkSortLeftToRight(o1);
                    merge();
                }
                if (!o2.isTerminal()) {
                    check &= checkSortLeftToRight(o2);
                    merge();

                }
                return o1.getLabel().compareTo(o2.getLabel());
            }
        };

        if (!node.isTerminal() && node.getChildren().size() == 1)
            result[0] &= checkSortLeftToRight(node.getChildren().getFirst());
        else if (!node.isTerminal()) Collections.sort(root, comparatorCheck);

        return result[0];
    }

    // function MAX-VALUE(state, alpha, beta) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- MIN_VALUE;
    // for each s in SUCCESSORS(state) do
    // v <- MAX(v, MIN-VALUE(s, alpha, beta))
    // if v >= beta then return v
    // alpha <- MAX(alpha, v)
    // return v
    public int maxValue(Node node, int alpha, int beta) {
        int v = alpha;
        Collections.sort(node.getChildren(), Node.letToRight);
        for (Node child : node.getChildren()) {
            if (alpha >= beta) {
                System.out.println("Cut: " + child.getLabel());
                continue;
            }

            if (!child.isTerminal()) {
                v = Math.max(alpha, minValue(child, alpha, beta));
            } else {
                v = Math.max(alpha, child.getValue());
            }

            alpha = Math.max(v, alpha); //3
        }

        return v;
    }

    // function MIN-VALUE(state, alpha , beta) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MIN(v, MAX-VALUE(s, alpha , beta))
    // if v <= alpha then return v
    // beta <- MIN(beta ,v)
    // return v
    public int minValue(Node node, int alpha, int beta) {
        int v = beta;

        Collections.sort(node.getChildren(), Node.letToRight);
        for (Node child : node.getChildren()) {
            if (beta <= alpha) {
                System.out.println("Cut: " + child.getLabel());
                continue;
            }

            if (!child.isTerminal()) {
                v = Math.min(beta, maxValue(child, alpha, beta));
            } else {
                v = Math.min(beta, child.getValue());
            }

            beta = Math.min(v, beta);
        }

        return v;
    }

    public int maxValue(Node node, int alpha, int beta, Comparator comparator) {
        int v = alpha;
        Collections.sort(node.getChildren(), comparator);
        for (Node child : node.getChildren()) {
            if (alpha >= beta) {
                System.out.println("Cut: " + child.getLabel());
                continue;
            }

            if (!child.isTerminal()) {
                v = Math.max(alpha, minValue(child, alpha, beta, comparator));
            } else {
                v = Math.max(alpha, child.getValue());
            }

            alpha = Math.max(v, alpha); //3
        }

        return v;
    }

    // function MIN-VALUE(state, alpha , beta) returns a utility value
    // if TERMINAL-TEST(state) then return UTILITY(state)
    // v <- Integer.MAX_VALUE
    // for each s in SUCCESSORS(state) do
    // v <- MIN(v, MAX-VALUE(s, alpha , beta))
    // if v <= alpha then return v
    // beta <- MIN(beta ,v)
    // return v
    public int minValue(Node node, int alpha, int beta, Comparator comparator) {
        int v = beta;

        Collections.sort(node.getChildren(), comparator);
        for (Node child : node.getChildren()) {
            if (beta <= alpha) {
                System.out.println("Cut: " + child.getLabel());
                continue;
            }

            if (!child.isTerminal()) {
                v = Math.min(beta, maxValue(child, alpha, beta, comparator));
            } else {
                v = Math.min(beta, child.getValue());
            }

            beta = Math.min(v, beta);
        }

        return v;
    }
}
