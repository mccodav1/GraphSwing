package gs.gui.leftpanel.nodepanels.modifydisplaypanels;

import gs.core.GraphSwing;

import javax.swing.*;

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