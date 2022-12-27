package gs.view.mainpane.right;

import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    private double viewPercent;

    public RightPanel() {
        super(new GridLayout(1, 1));
        setPreferredSize(new Dimension(600, 600));
    }

    public double getViewPercent() {
        return viewPercent;
    }

    public void setViewPercent(int i) {
        viewPercent = i;
    }

    public void drawGraph(Graph graph) {
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        Component viewPanel = viewer.addDefaultView(false);
        setViewPercent(1);
        removeAll();
        add(viewPanel);
        revalidate();
        repaint();
    }
}
