package panels.left;

import gs.GraphSwing;
import org.graphstream.graph.implementations.Graphs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ModifyDisplayPanel extends JPanel {
    private GraphSwing gs;
    public ModifyDisplayPanel(GraphSwing gs) {
        this.gs = gs;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton addNodeButton = new JButton("Add labels");
        JButton removeNodeButton = new JButton("Remove labels");
        add(addNodeButton);
        add(removeNodeButton);
        addNodeButton.addActionListener(e -> {
            gs.setShowLabels(true);
        });

        removeNodeButton.addActionListener(e -> {
            gs.setShowLabels(false);
        });
    }
}