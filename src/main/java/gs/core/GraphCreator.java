package gs.core;

import org.graphstream.graph.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphCreator {

    public static Graph getGraphFromFiles(String filename) throws IOException {
        Graph graph = new gsGraph("Single Graph");
        try {
            ArrayList<String[]> graphNodes = readFromBuffer(filename);
            for (String[] entry : graphNodes) {
                graph.addEdge(entry[0] + "_" + entry[1], entry[0], entry[1]);
            }
            graph.getEdgeSet().forEach(edge -> edge.addAttribute("length", 1));
            graph.getNodeSet().forEach(node -> node.addAttribute("label", node.getId()));
            return graph;
        } catch (IOException e) {
            return null;
        }
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

    /**
     * TODO: Re-implement with two-file methodology
     * @param graph
     * @return
     * @throws IOException
     */
    public static String saveFile(Graph graph) throws IOException {
        String fileName = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File("graph.csv"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (!selectedFile.getName().endsWith(".csv")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".csv");
            }
            fileName = selectedFile.getName();
            FileWriter fileWriter = new FileWriter(selectedFile);
            fileWriter.write("From,To\n");
            for (Node node : graph) {
                Iterator<Node> iterator = node.getNeighborNodeIterator();
                while (iterator.hasNext()) {
                    Node neighbor = iterator.next();
                    fileWriter.write(node.getId() + "," + neighbor.getId() + "\n");
                }
            }
            JOptionPane.showMessageDialog(null, "File " + fileName + " saved successfully.", "Saved", JOptionPane.INFORMATION_MESSAGE);
            fileWriter.close();
        }
        return fileName;
    }


    public static Graph getGraphFromFiles(String nodeFile, String linkFile) {
        Graph graph = new gsGraph("Single Graph");
        try {
            addNodesFromFile(graph, nodeFile);
            addLinksFromFile(graph, linkFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    private static void addLinksFromFile(Graph graph, String linkFile) throws IOException {
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

    private static void addNodesFromFile(Graph graph, String nodeFile) throws IOException {
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

    public static Graph getEmptyGraph() {
        return new gsGraph("Single Graph");
    }
}
