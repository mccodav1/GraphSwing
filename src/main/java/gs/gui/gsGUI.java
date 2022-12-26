package gs.gui;

import gs.core.GraphSwing;
import org.graphstream.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class gsGUI extends JFrame {

    private MainPane mainPane;

    public gsGUI(GraphSwing gs){
        mainPane = new MainPane(gs);
        add(mainPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setTitle("JTViewer");
        setMinimumSize(new Dimension(400, 400));
    }

    public void redraw(Graph graph) {
        mainPane.redraw(graph);
    }
}
