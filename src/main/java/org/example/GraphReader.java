package org.example;

import org.graphstream.graph.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphReader {

    public static Graph getGraph(String filename) throws IOException {
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

    private static ArrayList<String[]> readFromBuffer(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return read(reader);
    }

    private static ArrayList<String[]> readFromBuffer(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return read(reader);

    }

    private static ArrayList<String[]> read(BufferedReader reader) throws IOException {
        String currentLine = reader.readLine();
        currentLine = reader.readLine();
        ArrayList<String[]> strings = new ArrayList<>();
        while (currentLine != null) {
            String[] currentLineNames = new String[2];
            currentLineNames[0] = currentLine.split(",")[0];
            currentLineNames[1] = currentLine.split(",")[1];
            strings.add(currentLineNames);
            currentLine = reader.readLine();
        }
        return strings;

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

    public static Graph openFile() {
        Graph graph = null;
        String fileName = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileName = selectedFile.getName();
            if(fileName.toLowerCase().endsWith(".csv")){
                try {
                    graph = getGraph(selectedFile.getAbsolutePath());
                    JOptionPane.showMessageDialog(null, "File " + fileName + " opened successfully.", "Opened", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "File " + fileName + " could not be opened.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "CSV file expected. File " + fileName + " is not a csv file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            return null;
        }
        return graph;
    }
}
