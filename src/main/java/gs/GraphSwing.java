package gs;

import com.github.javafaker.Faker;
import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import panels.right.RightPanel;
import panels.left.LeftPanel;

import javax.swing.*;
import java.awt.*;

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
            leftPanel = new LeftPanel(this);
            rightPanel = new RightPanel(this);
            viewPanel = rightPanel.getViewPanel();

            JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
            mainPanel.setOneTouchExpandable(true);
            mainPanel.setEnabled(false);
            leftPanel.setMinimumSize(new Dimension(300, 50));
            mainPanel.setContinuousLayout(true);

            JFrame mainFrame = new JFrame();
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.add(mainPanel);
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
            mainFrame.setTitle("JTViewer");
            mainFrame.setMinimumSize(new Dimension(400, 400));
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //SwingUtilities.updateComponentTreeUI(mainFrame);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addRandomNodesWithNames(){
        for (int i = 0; i < NUM_RANDOM_NODES_TO_ADD; i++) {
            String name = new Faker().name().fullName();
            // add between 0 and 10 connections
            int numConnections = (int) (Math.random() * 10);
            graph.addNode(name);
            for (int j = 0; j < numConnections; j++) {
                //10% of the time, have the random name actually be an existing node's name
                String connectionName = "";
                if (Math.random() < .1) {
                    // get a random node from the graph
                    connectionName = graph.getNode((int) (Math.random() * graph.getNodeCount())).getId();
                } else {
                    connectionName = new Faker().name().fullName();
                }
                graph.addEdge(name+"_"+connectionName, name, connectionName);
            }
            setShowLabels(true);
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

    public void getGraphFromFiles(String nodeFile, String linkFile) {
        Graph newGraph = GraphCreator.getGraphFromFiles(nodeFile, linkFile);
        if (newGraph != null) {
            graph = newGraph;
            redraw();
        }
    }

    public ViewPanel getViewPanel() {
        return viewPanel;
    }

    public void redraw(){
        //viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        viewPanel = viewer.addDefaultView(false);
        rightPanel.removeAll();
        rightPanel.add(viewPanel);
        rightPanel.revalidate();
        rightPanel.repaint();

    }

    public void setShowLabels(boolean b) {
        graph.getNodeSet().forEach(node -> node.setAttribute("label", b ? node.getId() : ""));
    }

    public void repaint() {
        redraw();
    }
}

