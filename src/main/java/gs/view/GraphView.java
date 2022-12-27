package gs.view;

import gs.view.mainpane.MainPane;
import org.graphstream.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphView extends JFrame {

    private MainPane mainPane;

    public GraphView(){

        mainPane = new MainPane();
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

    public void clearNodeText() {
        mainPane.clearNodeText();
    }

    public JButton getClearButton() {
        return mainPane.getClearButton();
    }

    public JButton getAddButton() {
        return mainPane.getAddButton();
    }

    public String[] getNodesToAdd() {
        return mainPane.getNodesToAdd();
    }

    public String[] getConnectedNodesToAdd() {
        return mainPane.getConnectedNodesToAdd();
    }

    public JButton getRemoveNodesButton() {
        return mainPane.getRemoveNodesButton();
    }

    public JButton getOpenButton() {
        return mainPane.getOpenButton();
    }

    public JButton getSaveButton() {
        return mainPane.getSaveButton();
    }

    public JButton getRandomButton() {
        return mainPane.getRandomButton();
    }

    public JButton getAddLabelsButton() {
        return mainPane.getAddLabelsButton();
    }

    public JButton getRemoveLabelsButton() {
        return mainPane.getRemoveLabelsButton();
    }

    public JButton getCentralityButton() {
        return mainPane.getCentralityButton();
    }
}
