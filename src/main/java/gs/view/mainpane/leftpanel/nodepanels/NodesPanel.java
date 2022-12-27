package gs.view.mainpane.leftpanel.nodepanels;

import gs.view.mainpane.leftpanel.nodepanels.addnodepanels.AddNodePanel;
import gs.view.mainpane.leftpanel.nodepanels.modifydisplaypanels.ModifyDisplayPanel;
import gs.view.mainpane.leftpanel.nodepanels.openfilepanels.OpenFilePanel;
import gs.view.mainpane.leftpanel.nodepanels.sliderpanels.SliderPanel;

import javax.swing.*;
import java.awt.*;

public class NodesPanel extends JPanel {
    private final AddNodePanel addNodePanel;
    private final SliderPanel sliderPanel;
    private final OpenFilePanel openFilePanel;
    private final ModifyDisplayPanel modifyDisplayPanel;

    public NodesPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Add Node Panel
        addNodePanel = new AddNodePanel();
        add(addNodePanel);

        //Modify Display Panel
        modifyDisplayPanel = new ModifyDisplayPanel();
        add(modifyDisplayPanel);

        //Slider Panel
        sliderPanel = new SliderPanel();
        add(sliderPanel);

        //Open file panel
        add(Box.createVerticalGlue());
        openFilePanel = new OpenFilePanel();
        add(openFilePanel);

        // Master panel setup
        setMaximumSize(new Dimension(200, 2000));
        setPreferredSize(new Dimension(300, 600));
    }

    public JButton getAddButton() {
        return addNodePanel.getAddButton();
    }

    private JSlider getSlider() {
        return sliderPanel.getSlider();
    }

    public JButton getRemoveNodesButton() {
        return addNodePanel.getRemoveNodesButton();
    }

    public JButton getOpenButton() {
        return openFilePanel.getOpenButton();
    }

    public JButton getSaveButton() {
        return openFilePanel.getSaveButton();
    }

    public JButton getRandomButton() {
        return addNodePanel.getRandomButton();
    }

    public void clearNodeText() {
        addNodePanel.clearTextAreas();
    }

    public JButton getClearButton() {
        return addNodePanel.getClearButton();
    }

    public String[] getNodesToAdd() {
        return addNodePanel.getNodesToAdd();
    }

    public String[] getConnectedNodesToAdd() {
        return addNodePanel.getConnectedNodesToAdd();
    }

    public JButton getAddLabelsButton() {
        return modifyDisplayPanel.getAddLabelsButton();
    }

    public JButton getRemoveLabelsButton() {
        return modifyDisplayPanel.getRemoveLabelsButton();
    }
}
