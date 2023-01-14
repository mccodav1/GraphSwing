package gs.core;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

public class AlgorithmCalculator {
//TODO implement
    // Awareness
    // Connector
    // Diversity
    // Influence
    // Integration
    // Opportunity
    // Link Similarity
    // Key bridges

    /*
    public static int getNodeCentrality(Node node, Graph graph) {
        Path path;
        int centrality = 0;
        for (Node node1 : graph.getNodeSet()) {
            for (Node node2 : graph.getNodeSet()) {
                if (node1 != node2) {
                    path = findShortestPath(node1, node2, graph);
                    if (path.contains(node)) {
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
        for (Node node : graph.getNodeSet()) {
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

    public static Path findShortestPath(String from, String to, Graph graph) {
        Dijkstra dijkstra = new Dijkstra(Element.EDGE, null, "length");
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode(from));
        dijkstra.compute();
        return dijkstra.getPath(graph.getNode(to));
    }

    public static Path findShortestPath(Node fromNode, Node toNode, Graph graph) {
        return findShortestPath(fromNode.toString(), toNode.toString(), graph);
    }
*/
}
