package gs;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import panels.LeftPanel;
import panels.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GraphSwing {

    private static final int NUM_RANDOM_NODES_TO_ADD = 10;
    private Graph graph;

    private String DIRECTORY = "src/main/resources/";

    private RightPanel rightPanel;

    private Viewer viewer;

    private ViewPanel viewPanel;

    private LeftPanel leftPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new GraphSwing()::display);
    }

    private void display() {
        try {
            graph = new MySingleGraph("Single Graph");
            JFrame mainFrame = new JFrame();
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            leftPanel = new LeftPanel(this);
            rightPanel = new RightPanel(this);
            viewPanel = rightPanel.getViewPanel();

            JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
            mainPanel.setOneTouchExpandable(true);
            leftPanel.setMinimumSize(new Dimension(100, 50));
            rightPanel.setMinimumSize(new Dimension(600, 600));
            leftPanel.setMaximumSize(new Dimension(300, 2000));
            mainPanel.setContinuousLayout(true);

            mainFrame.add(mainPanel);
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
            mainFrame.setTitle("JTViewer");
            mainFrame.setMinimumSize(new Dimension(400, 400));
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");


            mainFrame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    Dimension d = mainFrame.getSize();
                    Dimension minD = mainFrame.getMinimumSize();
                    if (d.width < minD.width)
                        d.width = minD.width;
                    if (d.height < minD.height)
                        d.height = minD.height;
                    mainFrame.setSize(d);
                    rightPanel.setMinimumSize(new Dimension(mainFrame.getSize().width-300, 600));
                    redraw();
                }
            });


            addRandomNodes();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addRandomNodes() {
        for (int i = 0; i < NUM_RANDOM_NODES_TO_ADD; i++) {
            int random = (int) (Math.random() * NUM_RANDOM_NODES_TO_ADD * 50);
            // 10% of the time, add the node with no connection
            if (Math.random() < 0.1) {
                graph.addNode(String.valueOf(random));
            } else {
                graph.addEdge(i + Integer.toString(random), Integer.toString(i), Integer.toString(random));
            }
        }
        redraw();
    }

    public Graph getGraph() {
        return graph;
    }

    public void openFile() {
        Graph newGraph = GraphReader.openFile();
        if (newGraph != null) {
            graph = newGraph;
            redraw();
        }
    }

    public ViewPanel getViewPanel() {
        return viewPanel;
    }

    public void redraw(){
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        viewPanel = viewer.addDefaultView(false);
        rightPanel.removeAll();
        rightPanel.add(viewPanel);
        rightPanel.revalidate();
        rightPanel.repaint();

    }
}

