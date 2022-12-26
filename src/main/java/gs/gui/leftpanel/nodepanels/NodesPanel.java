package gs.gui.leftpanel.nodepanels;

import gs.core.GraphCreator;
import gs.core.GraphSwing;
import gs.gui.leftpanel.nodepanels.addnodepanels.AddNodePanel;
import gs.gui.leftpanel.nodepanels.modifydisplaypanels.ModifyDisplayPanel;
import gs.gui.leftpanel.nodepanels.openfilepanels.OpenFilePanel;
import gs.gui.leftpanel.nodepanels.openfilepopups.OpenFilePopup;
import gs.gui.leftpanel.nodepanels.sliderpanels.SliderPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class NodesPanel extends JPanel {
    private AddNodePanel addNodePanel;
    private SliderPanel sliderPanel;
    private OpenFilePanel openFilePanel;
    private ModifyDisplayPanel modifyDisplayPanel;
    private GraphSwing graphSwing;

    public NodesPanel(GraphSwing graphSwing) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Add Node Panel
        addNodePanel = new AddNodePanel();
        add(addNodePanel);

        //Modify Display Panel
        modifyDisplayPanel = new ModifyDisplayPanel(graphSwing);
        add(modifyDisplayPanel);

        //Slider Panel
        sliderPanel = new SliderPanel();
        add(sliderPanel);

        //Open file panel
        add(Box.createVerticalGlue());
        openFilePanel = new OpenFilePanel();
        add(openFilePanel);

        // Master panel setup
        setMaximumSize(new Dimension(200, 2000));
        setPreferredSize(new Dimension(300, 600));
        addActionListeners();
        this.graphSwing = graphSwing;
    }

    private void addActionListeners() {

        getAddButton().addActionListener(e -> {
            String[] nodesToAdd = addNodePanel.getNodesToAdd();
            if (nodesToAdd != null) {
                String[] connectedNodes = addNodePanel.getConnectedNodesToAdd();
                for (String node : nodesToAdd) {
                    for (String connectedNode : connectedNodes) {
                        graphSwing.getGraph().addEdge(node + connectedNode, node, connectedNode).addAttribute("length", 1);
                        //graphSwing.getGraph().getNode(connectedNode).setAttribute("label", connectedNode);
                    }
                    graphSwing.getGraph().getNode(node).setAttribute("label", node);
                }
                graphSwing.redraw();
            }
            addNodePanel.clearTextAreas();
        });

        getRemoveNodesButton().addActionListener(e -> {
            while (graphSwing.getGraph().getNodeCount() > 0) {
                graphSwing.getGraph().removeNode(0);
            }
        });

        getOpenButton().addActionListener(e -> {
            //graphSwing.openFile(); deprecated functionality
            OpenFilePopup openFilePopup = new OpenFilePopup(graphSwing);
            openFilePopup.setVisible(true);


        });

        getSaveButton().addActionListener(e -> {
            try {
                GraphCreator.saveFile(graphSwing.getGraph());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        getRandomButton().addActionListener(e -> {
            graphSwing.addRandomNodesWithNames();
        });

    }

    public JButton getAddButton() {
        return addNodePanel.getAddButton();
    }

    private JSlider getSlider() {
        return sliderPanel.getSlider();
    }

    public JButton getRemoveNodesButton(){
        return addNodePanel.getRemoveNodesButton();
    }

    public JButton getOpenButton() {
        return openFilePanel.getOpenButton();
    }

    public JButton getSaveButton() {
        return openFilePanel.getSaveButton();
    }

    public JButton getRandomButton() {
        return addNodePanel.getRandomButton();
    }
}
