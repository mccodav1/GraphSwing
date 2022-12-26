package gs.core;

import com.github.javafaker.Faker;
import gs.gui.gsGUI;
import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.ViewPanel;

public class GraphSwing {

    private static final int NUM_RANDOM_NODES_TO_ADD = 10;
    private Graph graph;
    private gsGUI gui;

    public static void main(String[] args) {
        GraphSwing gs = new GraphSwing();
        gs.display();
    }

    private void display() {
        graph = GraphCreator.getEmptyGraph();
        gui = new gsGUI(this);
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

    public void redraw(){
        gui.redraw(graph);
    }

    public void setShowLabels(boolean b) {
        graph.getNodeSet().forEach(node -> node.setAttribute("label", b ? node.getId() : ""));
    }

}

