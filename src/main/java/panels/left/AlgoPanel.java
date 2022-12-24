package panels.left;

import algos.AlgorithmCalculator;
import gs.GraphSwing;
import javafx.application.Platform;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AlgoPanel extends JPanel {
    private GraphSwing gs;
    public AlgoPanel(GraphSwing gs){
        // set layout to new grid layout with 1 column and 2 rows
        setLayout(new GridLayout(2, 1, 10, 10));
        setMaximumSize(new Dimension(300, 2000));
        setPreferredSize(new Dimension(300, 600));
        this.gs=gs;

        //create a pane for top half of grid
        JPanel topPane = new JPanel();
        // inside this pane, make a 3x3 grid of buttons
        topPane.setLayout(new GridLayout(3, 3, 10, 10));
        // add buttons to top pane
        JButton centralityButton = new JButton("Centrality");
        topPane.add(centralityButton);
        topPane.add(new JButton("Button 2"));
        topPane.add(new JButton("Button 3"));
        topPane.add(new JButton("Button 4"));
        topPane.add(new JButton("Button 5"));
        topPane.add(new JButton("Button 6"));
        topPane.add(new JButton("Button 7"));
        topPane.add(new JButton("Button 8"));

        // create a scrolling pane for bottom half of grid
        // fill this pane with a text area
        JTextArea textArea = new JTextArea("Test");
        textArea.setEditable(false);
        JScrollPane bottomPane = new JScrollPane(textArea);
        bottomPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(topPane);
        this.add(bottomPane);

        centralityButton.addActionListener(e -> {
            String[] centralities = AlgorithmCalculator.getCentralityForEach(gs.getGraph());
            String newString = "Centralities:\n";
            for(String s : centralities){
                newString += s + "\n";
            }
            textArea.setText(newString);

        });
    }

}
