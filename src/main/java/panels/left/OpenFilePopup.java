package panels.left;

import gs.GraphCreator;
import gs.GraphSwing;
import org.graphstream.graph.Graph;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class OpenFilePopup extends JPanel {


    JLabel nodeLabel;
    JLabel linkLabel;
    JTextField nodeTF;
    JTextField linkTF;
    JButton nodeButton;
    JButton linkButton;
    JButton cancelButton;
    JButton openButton;

    GraphSwing gs;

    public OpenFilePopup(GraphSwing gs) {

        this.gs = gs;
        setLayout(new GridLayout(2, 1, 10, 10));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setSize(400, 200);
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        panel.setBorder(padding);


        GridBagConstraints c = new GridBagConstraints();

        nodeLabel = new JLabel("Node File:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(nodeLabel, c);

        nodeTF = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        nodeTF.setEditable(false);
        panel.add(nodeTF, c);

        nodeButton = new JButton("Browse");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 0;
        panel.add(nodeButton, c);


        linkLabel = new JLabel("Link File:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(linkLabel, c);

        linkTF = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        linkTF.setEditable(false);
        panel.add(linkTF, c);

        linkButton = new JButton("Browse");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 1;
        panel.add(linkButton, c);

        add(panel);

        JPanel buttonPanel = new JPanel();

        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);

        openButton = new JButton("Open");
        openButton.setEnabled(false);
        buttonPanel.add(openButton);

        add(buttonPanel);


        JFrame parent = new JFrame();
        parent.setTitle("Open Node / Link Files");
        parent.add(this);
        parent.pack();
        parent.setLocationRelativeTo(this);
        parent.setVisible(true);

        // node button should open a jfile chooser
        nodeButton.addActionListener(e -> {
            openFile(nodeTF);
            if (nodeTF.getText().length() > 0 && linkTF.getText().length() > 0) {
                openButton.setEnabled(true);
            }
        });

        // link button should open a jfile chooser
        linkButton.addActionListener(e -> {
            openFile(linkTF);
            if (nodeTF.getText().length() > 0 && linkTF.getText().length() > 0) {
                openButton.setEnabled(true);
            }
        });

        cancelButton.addActionListener(e -> parent.dispose());

        openButton.addActionListener(e -> {
            gs.getGraphFromFiles(nodeTF.getText(), linkTF.getText());
            parent.dispose();
        });
    }

     // only enable the open button if both text fields have a file path


    private void openFile(JTextField tf) {
        String fileName = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
        int returnValue = fileChooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            fileName = fileChooser.getSelectedFile().getAbsolutePath();
            if(fileName.toUpperCase().endsWith(".CSV")) {
                tf.setText(fileName);
            }
        }


    }
}







