package panels;

import gs.GraphSwing;

import javax.swing.*;
import java.awt.*;

public class TabPanel extends JTabbedPane {

    public TabPanel(GraphSwing gs){
        JComponent tabOne = new LeftPanel(gs);
        add(tabOne, "Nodes");
        add(new AlgoPanel(gs), "Algorithms");
        setMaximumSize(new Dimension(200, 2000));
        setPreferredSize(new Dimension(300, 600));
    }

}
