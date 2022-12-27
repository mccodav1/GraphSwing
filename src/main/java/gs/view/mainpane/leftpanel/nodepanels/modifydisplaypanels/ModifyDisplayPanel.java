package gs.view.mainpane.leftpanel.nodepanels.modifydisplaypanels;

import javax.swing.*;

public class ModifyDisplayPanel extends JPanel {
    private final JButton addLabelsButton;
    private final JButton removeLabelsButton;

    public ModifyDisplayPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        addLabelsButton = new JButton("Add labels");
        removeLabelsButton = new JButton("Remove labels");
        add(addLabelsButton);
        add(removeLabelsButton);

    }

    public JButton getAddLabelsButton() {
        return addLabelsButton;
    }

    public JButton getRemoveLabelsButton() {
        return removeLabelsButton;
    }
}