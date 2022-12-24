package panels.left;

import gs.GraphCreator;
import gs.GraphSwing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class NodesPanel extends JPanel {

    private AddNodePanel addNodePanel;

    private SliderPanel sliderPanel;

    private OpenFilePanel openFilePanel;

    private GraphSwing graphSwing;

    private ModifyDisplayPanel modifyDisplayPanel;

    public NodesPanel(GraphSwing graphSwing) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addNodePanel = new AddNodePanel();
        add(addNodePanel);

        // add a panel to hold two buttons side by side
        modifyDisplayPanel = new ModifyDisplayPanel(graphSwing);
        add(modifyDisplayPanel);



        sliderPanel = new SliderPanel();
        add(sliderPanel);
        add(Box.createVerticalGlue());
        openFilePanel = new OpenFilePanel();
        add(openFilePanel);
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
            graphSwing.openFile();

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

        getSlider().addChangeListener(new SliderListener());

    }

    class SliderListener implements ChangeListener {

        /**
         * Invoked when the target of the listener has changed its state.
         *
         * @param e a ChangeEvent object
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            // when source.getValue() = 0, value = .05
            // when source.getValue() = 100, value = 5
            double value = (source.getValue() / 100.0) * 4.95 + .05;
            graphSwing.getViewPanel().getCamera().setViewPercent(value);
            graphSwing.redraw();
        }
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
