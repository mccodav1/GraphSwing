package gs.core;

import com.github.javafaker.Faker;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import javax.xml.soap.SOAPElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class should contain the data and business logic for the application.
 * For example, if you're creating a graph visualization application, the model
 * might contain the graph data and methods for manipulating the graph.
 */
public class GraphModel {

    private static final int NUM_RANDOM_NODES_TO_ADD = 10;
    private GSGraph graph;

    public GraphModel() {
        graph = getEmptyGraph();
    }

    private GSGraph getEmptyGraph() {
        return new GSGraph("Single Graph");
    }

    public void addRandomNodes(){
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
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public void generateGraphFromFiles(String nodeFile, String linkFile) {
        GSGraph newGraph = new GSGraph("Single Graph");
        try{
            addNodesFromFile(newGraph, nodeFile);
            addLinksFromFile(newGraph, linkFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (newGraph!=null) {
            graph = newGraph;
        }
    }

    private void addLinksFromFile(Graph graph, String linkFile) throws IOException {
        ArrayList<String[]> graphLinks = readFromBuffer(linkFile);

        String[] linkLabels = graphLinks.get(0);
        for (int entry = 1; entry < graphLinks.size(); entry++) {
            String[] link = graphLinks.get(entry);

            String sourceNodeLabel = link[0];
            graph.addNode(sourceNodeLabel).setAttribute("label", sourceNodeLabel);

            String targetNodeLabel = link[1];
            graph.addNode(targetNodeLabel).setAttribute("label", targetNodeLabel);

            Edge e = graph.addEdge(sourceNodeLabel + "_" + targetNodeLabel, sourceNodeLabel, targetNodeLabel);
            if (e==null) {
                e = graph.getEdge(targetNodeLabel + "_" + sourceNodeLabel);
            }
            for (int i = 2; i < link.length; i++) {
                String label = linkLabels[i];
                String value = link[i];
                e.addAttribute(label, value);
            }
        }
        System.out.println("Edges added");
    }

    private void addNodesFromFile(Graph graph, String nodeFile) throws IOException {
        // create nodes and set attributes
        ArrayList<String[]> graphNodes = readFromBuffer(nodeFile);
        // first string[] in graphNodes contains column labels
        // each subsequent string[] in graphNodes contains a node
        String[] nodeLabels = graphNodes.get(0);
        // entry 0 contains labels; rest contain data
        for (int entry = 1; entry < graphNodes.size(); entry++) {
            String label = graphNodes.get(entry)[0];
            Node n = graph.addNode(label);
            n.setAttribute("label", label);
            for (int j = 1; j < nodeLabels.length; j++) {
                n.setAttribute(nodeLabels[j], graphNodes.get(entry)[j]);
            }
        }
        System.out.println("Nodes added");
    }

    private static ArrayList<String[]> readFromBuffer(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String[]> entriesList = new ArrayList<>();
        String line = reader.readLine();
        if(line==null) return null;
        String[] columnTitles = line.split(",");
        int numColumns = columnTitles.length;
        entriesList.add(columnTitles);
        String currentLine = reader.readLine();
        while (currentLine != null) {
            String[] currentLineArray = currentLine.split(",");
            if (currentLineArray.length != numColumns) {
                throw new IOException("File is not formatted correctly");
            }
            if(!currentLineArray[0].equals("")) {
                entriesList.add(currentLineArray);
            }
            currentLine = reader.readLine();
        }
        return entriesList;
    }

    public void setShowLabels(boolean b) {
        graph.getNodeSet().forEach(node -> node.setAttribute("label", b ? node.getId() : ""));
    }

    public Edge addEdge(String s, String node, String connectedNode) {
        Edge e = graph.addEdge(s, node, connectedNode);
        return e;
    }

    public void setNodeAttribute(String label, String node) {
        graph.getNode(node).setAttribute(label, node);
    }


    public int getNodeCount() {
        return graph.getNodeCount();
    }

    public void removeNode(int i) {
        graph.removeNode(i);
    }

    public void saveFile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

