package panels.left;

import gs.GraphSwing;

import javax.swing.*;

public class LeftPanel extends JTabbedPane {

    public LeftPanel(GraphSwing gs){
        JComponent nodesTab = new NodesPanel(gs);
        JComponent algoTab = new AlgoPanel(gs);
        addTab("Nodes", nodesTab);
        addTab("Algorithms", algoTab);
    }

}
