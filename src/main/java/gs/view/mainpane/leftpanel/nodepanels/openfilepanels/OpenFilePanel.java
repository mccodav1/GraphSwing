package gs.view.mainpane.leftpanel.nodepanels.openfilepanels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OpenFilePanel extends JPanel {

    private final JButton openButton;
    private final JButton saveButton;

    public OpenFilePanel() {
        setMaximumSize(new Dimension(300, 100));
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        setBorder(padding);
        setLayout(new GridLayout(1, 2, 10, 10));
        openButton = new JButton("Open");
        openButton.setPreferredSize(new Dimension(100, 30));
        add(openButton);
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        add(saveButton);
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}