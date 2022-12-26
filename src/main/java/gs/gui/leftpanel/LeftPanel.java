package gs.gui.leftpanel;

import gs.core.GraphSwing;
import gs.gui.leftpanel.nodepanels.NodesPanel;
import gs.gui.leftpanel.algopanels.AlgoPanel;

import javax.swing.*;

public class LeftPanel extends JTabbedPane {

    public LeftPanel(GraphSwing gs){
        NodesPanel nodesTab = new NodesPanel(gs);
        AlgoPanel algoTab = new AlgoPanel(gs);
        addTab("Nodes", nodesTab);
        addTab("Algorithms", algoTab);
    }

}
