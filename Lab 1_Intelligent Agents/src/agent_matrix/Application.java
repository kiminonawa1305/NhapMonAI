package agent_matrix;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Application extends JFrame {
    private static final int SIZE_CELL = 100;
    private static final ImageIcon AGENT_ICON = new ImageIcon("src\\agent_matrix\\agent.gif"),
            OBSTACLE_ICON = new ImageIcon("src\\agent_matrix\\obstacle.gif");

    private Map<String, JLabel> mapLabel;
    private JPanel panelDisplay;

    private Environment environment;
    private JLabel score;

    public Application(Environment environment) {
        this.environment = environment;
        mapLabel = new HashMap<>();
        this.init();
        this.setVisible(true);
    }

    public void init() {
        this.setTitle("Agent");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int rowCount = this.environment.getEnvState().getRowCount();
        int columnCount = this.environment.getEnvState().getColumnCount();
        this.getContentPane().setLayout(new BorderLayout());
        this.setResizable(false);

        panelDisplay = new JPanel(new GridLayout(rowCount, columnCount));
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                JLabel label = setUpCell(row, column);
                panelDisplay.add(label);
                mapLabel.put(row + ";" + column, label);
            }
        }
        this.getContentPane().add(panelDisplay, BorderLayout.CENTER);

        JPanel panelSouth = new JPanel(new BorderLayout());

        JButton action = new JButton("Action");
        action.setPreferredSize(new Dimension(SIZE_CELL * (columnCount-1), 50));
        action.addActionListener(e -> {
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Action anAction = environment.step();
                    display(anAction);

                    if (environment.isDone()) {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });

            timer.start();
        });
        panelSouth.add(action,BorderLayout.EAST);

        score = new JLabel("Score: 0");
        panelSouth.add(score,BorderLayout.WEST);


        this.getContentPane().add(panelSouth, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void display(Action anAction) {
        score.setText("Score: " + environment.getScore());

        Location agentLocation = environment.getEnvState().getAgentLocation();
        int row = agentLocation.getRow();
        int column = agentLocation.getCol();
        JLabel label = mapLabel.get(row + ";" + column);

        if (anAction.equals(Environment.SUCK_DIRT)) {
            label.setBackground(Color.white);
            return;
        }

        label.setIcon(AGENT_ICON);
        if (anAction.equals(Environment.MOVE_LEFT)) {
            JLabel labelBefor = mapLabel.get(row + ";" + (column + 1));
            labelBefor.setIcon(null);
        }

        if (anAction.equals(Environment.MOVE_RIGHT)) {
            JLabel labelAfter = mapLabel.get(row + ";" + (column - 1));
            labelAfter.setIcon(null);
        }

        if (anAction.equals(Environment.MOVE_UP)) {
            JLabel labelAfter = mapLabel.get((row + 1) + ";" + column);
            labelAfter.setIcon(null);
        }

        if (anAction.equals(Environment.MOVE_DOWN)) {
            JLabel labelAfter = mapLabel.get((row - 1) + ";" + column);
            labelAfter.setIcon(null);
        }
    }

    public JLabel setUpCell(int row, int col) {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(SIZE_CELL, SIZE_CELL));
        label.setBorder(new LineBorder(Color.BLACK));
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        Location location = new Location(row, col);
        if (this.environment.getEnvState().isAgentLocation(location)) {
            System.out.println("Agent location: " + location);
            label.setIcon(AGENT_ICON);
        }

        if (this.environment.getEnvState().isObstacleLocation(location)) {
            label.setIcon(OBSTACLE_ICON);
        }

        if (this.environment.getEnvState().isDirtyLocation(location)) {
            label.setBackground(Color.darkGray);
        }

        return label;
    }
}
