package algos;

import gs.GraphCreator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.IOException;
import java.util.ArrayList;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Path;

public class AlgorithmCalculator {

    // Awareness
    // Connector
    // Diversity
    // Influence
    // Integration
    // Opportunity
    // Link Similarity
    // Key bridges

    public static void main(String[] args) throws IOException {
        Graph graph = GraphCreator.getGraphFromFiles("src/main/resources/links.csv");
        String[] impNodes = getImportantNodes(graph);
        for (String node : impNodes) {
            System.out.println(node);
        }
    }


    public static String[] getImportantNodes(Graph graph) {
        int numberOfNodes = graph.getNodeCount();
        int centralitySum = 0;
        setCentralityForAllNodes(graph);
        for(Node node : graph.getNodeSet()) {
            centralitySum += (int) node.getAttribute("centrality");
        }
        int averageCentrality = centralitySum / numberOfNodes;
        ArrayList<String> importantNodes = new ArrayList<>();
        for(Node node : graph.getNodeSet()) {
            if((int) node.getAttribute("centrality") > averageCentrality) {
                importantNodes.add(node.getId());
            }
        }
        return importantNodes.toArray(new String[importantNodes.size()]);
    }

    public static int getNodeCentrality(Node node, Graph graph) {
        Path path;
        int centrality = 0;
        for (Node node1 : graph.getNodeSet()) {
            for (Node node2 : graph.getNodeSet()) {
                if (node1 != node2) {
                    path = findShortestPath(node1, node2, graph);
                    if(path.contains(node)) {
                        centrality++;
                    }
                }
            }
        }
        return centrality;
    }

    public static String[] getCentralityForEach(Graph graph) {
        String[] centralityForEach = new String[graph.getNodeCount()];
        int i = 0;
        for(Node node : graph.getNodeSet()) {
            centralityForEach[i] = node.getId() + "," + getNodeCentrality(node, graph);
            i++;
        }
        return centralityForEach;
    }

    public static void setCentralityForAllNodes(Graph graph) {
        for (Node node : graph.getNodeSet()) {
            node.addAttribute("centrality", getNodeCentrality(node, graph));
        }
    }

    public static Path findShortestPath(String from, String to, Graph graph){
        Dijkstra dijkstra = new Dijkstra(Element.EDGE, null, "length");
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode(from));
        dijkstra.compute();
        Path path = dijkstra.getPath(graph.getNode(to));
        return path;
    }

    public static Path findShortestPath(Node fromNode, Node toNode, Graph graph){
        return findShortestPath(fromNode.toString(), toNode.toString(), graph);
    }

}
