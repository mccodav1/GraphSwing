package gs;

import org.graphstream.graph.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphCreator {

    public static Graph getGraphFromFiles(String filename) throws IOException {
        Graph graph = new MySingleGraph("Single Graph");
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

    /*
    public static Graph getGraph(File file) throws IOException {
        Graph graph = new MySingleGraph("Single Graph");
        try {
            ArrayList<String[]> graphNodes = readFromBuffer(file);
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

     */

    private static ArrayList<String[]> readFromBuffer(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return read(reader);
    }

    /**
     * Reads from buffered reader and creates an ArrayList of String arrays
     * The first entry in the list represents the column titles
     * The rest of the entries represent the data corresponding to the column titles
     * The intent is to be read from a CSV file in the format:
     *
     *  (start of file)
     *  title1,title2,title3
     *  data1,data2,data3
     *  ...
     *  data1,data2,data3
     *  (end of file)
     *
     * @param reader the reader to read from
     * @return  an ArrayList of String arrays, with the first entry representing column titles and subsequent entries
     * representing lines of data
     * @throws IOException  If file is malformed (columns don't line up)
     */
    private static ArrayList<String[]> read(BufferedReader reader) throws IOException {
        // reading first line tells us how many columns we have in file
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
        Graph graph = new MySingleGraph("Single Graph");
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
}
