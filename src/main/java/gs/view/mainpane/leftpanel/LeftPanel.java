package gs.view.mainpane.leftpanel;

import gs.view.mainpane.leftpanel.algopanels.AlgoPanel;
import gs.view.mainpane.leftpanel.nodepanels.NodesPanel;

import javax.swing.*;

public class LeftPanel extends JTabbedPane {

    private final NodesPanel nodesPanel;
    private final AlgoPanel algoPanel;


    public LeftPanel() {
        nodesPanel = new NodesPanel();
        algoPanel = new AlgoPanel();
        addTab("Nodes", nodesPanel);
        addTab("Algorithms", algoPanel);
    }

    public void clearNodeText() {
        nodesPanel.clearNodeText();
    }

    public JButton getClearButton() {
        return nodesPanel.getClearButton();
    }

    public JButton getAddButton() {
        return nodesPanel.getAddButton();
    }

    public String[] getNodesToAdd() {
        return nodesPanel.getNodesToAdd();
    }

    public String[] getConnectedNodesToAdd() {
        return nodesPanel.getConnectedNodesToAdd();
    }

    public JButton getRemoveNodesButton() {
        return nodesPanel.getRemoveNodesButton();
    }

    public JButton getOpenButton() {
        return nodesPanel.getOpenButton();
    }

    public JButton getSaveButton() {
        return nodesPanel.getSaveButton();
    }

    public JButton getRandomButton() {
        return nodesPanel.getRandomButton();
    }

    public JButton getAddLabelsButton() {
        return nodesPanel.getAddLabelsButton();
    }

    public JButton getRemoveLabelsButton() {
        return nodesPanel.getRemoveLabelsButton();
    }

    public JButton getCentralityButton() {
        return algoPanel.getCentralityButton();
    }
}
