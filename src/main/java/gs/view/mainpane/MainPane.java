package gs.view.mainpane;

import gs.view.mainpane.leftpanel.LeftPanel;
import gs.view.mainpane.right.RightPanel;
import org.graphstream.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class MainPane extends JSplitPane {

    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainPane() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();
        setLeftComponent(leftPanel);
        setRightComponent(rightPanel);
        setOneTouchExpandable(true);
        setEnabled(false);
        leftPanel.setMinimumSize(new Dimension(300, 50));
        setContinuousLayout(true);
    }

    public void redraw(Graph graph) {
        rightPanel.drawGraph(graph);
    }


    public void clearNodeText() {
        leftPanel.clearNodeText();
    }

    public JButton getClearButton() {
        return leftPanel.getClearButton();
    }

    public JButton getAddButton() {
        return leftPanel.getAddButton();
    }

    public String[] getNodesToAdd() {
        return leftPanel.getNodesToAdd();
    }

    public String[] getConnectedNodesToAdd() {
        return leftPanel.getConnectedNodesToAdd();
    }

    public JButton getRemoveNodesButton() {
        return leftPanel.getRemoveNodesButton();
    }

    public JButton getOpenButton() {
        return leftPanel.getOpenButton();
    }

    public JButton getSaveButton() {
        return leftPanel.getSaveButton();
    }

    public JButton getRandomButton() {
        return leftPanel.getRandomButton();
    }

    public JButton getAddLabelsButton() {
        return leftPanel.getAddLabelsButton();
    }

    public JButton getRemoveLabelsButton() {
        return leftPanel.getRemoveLabelsButton();
    }

    public JButton getCentralityButton() {
        return leftPanel.getCentralityButton();
    }
}
