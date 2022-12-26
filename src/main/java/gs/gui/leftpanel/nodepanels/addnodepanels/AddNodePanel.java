package gs.gui.leftpanel.nodepanels.addnodepanels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddNodePanel extends JPanel {
    private final JTextField nodesToAddField;
    private final JTextField connectedNodes;

    private final JButton addButton;

    private final JButton removeButton;

    private final JButton randomButton;


    public AddNodePanel() {
        setMaximumSize(new Dimension(300, 100));
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        setBorder(padding);
        setLayout(new GridLayout(4, 2, 10, 10));
        add(new JLabel("Add a node by name:", SwingConstants.RIGHT));
        nodesToAddField = new JTextField(25);
        add(nodesToAddField);
        add(new JLabel("... and connect to:", SwingConstants.RIGHT));
        connectedNodes = new JTextField(25);
        add(connectedNodes);
        JButton clearButton = new JButton("Clear Text");
        add(clearButton);
        addButton = new JButton("Add Node");
        add(addButton);
        removeButton = new JButton("Remove All");
        add(removeButton);
        randomButton = new JButton("Add Random");
        add(randomButton);

        clearButton.addActionListener(e -> {
            nodesToAddField.setText("");
            connectedNodes.setText("");
        });
        nodesToAddField.setText("");
        connectedNodes.setText("");
    }

    public JButton getRemoveNodesButton() {
        return removeButton;
    }

    public String[] getNodesToAdd() {
        String nodes = nodesToAddField.getText();
        if(nodes.trim().isEmpty()) {
            return null;
        }
        String[] nodesArray = nodes.split(",");
        for(int i = 0; i < nodesArray.length; i++){
            nodesArray[i] = nodesArray[i].trim();
        }
        return nodesArray;
    }

    public String[] getConnectedNodesToAdd() {
        String nodes = connectedNodes.getText();
        String[] nodesArray = nodes.split(",");
        for (int i = 0; i < nodesArray.length; i++) {
            nodesArray[i] = nodesArray[i].trim();
        }
        return nodesArray;
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void clearTextAreas() {
        nodesToAddField.setText("");
        connectedNodes.setText("");
    }
}