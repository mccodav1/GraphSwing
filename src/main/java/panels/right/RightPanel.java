package panels.right;

import gs.GraphSwing;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Camera;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class RightPanel extends JPanel {

    private GraphSwing graphSwing;

    private ViewPanel viewPanel;

    private Viewer viewer;

    private double viewPercent;

    public RightPanel(GraphSwing graphSwing) {
        super(new GridLayout(1, 1));
        this.graphSwing = graphSwing;
        setPreferredSize(new Dimension(600, 600));
        viewer = new Viewer(graphSwing.getGraph(), Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        viewPanel = viewer.addDefaultView(false);
        viewPercent = 1;
        add(viewPanel);
        addActionListeners();

    }

    private void addActionListeners() {
        this.addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                if (viewPercent > .05) viewPercent -= .05;
            } else {
                viewPercent += .05;
            }
            graphSwing.getViewPanel().getCamera().setViewPercent(viewPercent);
        });

        // add listener for right arrow key
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
                    Camera camera = graphSwing.getViewPanel().getCamera();
                    camera.setViewCenter(camera.getViewCenter().x + 10, camera.getViewCenter().y, 0);
                } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    Camera camera = graphSwing.getViewPanel().getCamera();
                    camera.setViewCenter(camera.getViewCenter().x - 10, camera.getViewCenter().y, 0);
                } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                    Camera camera = graphSwing.getViewPanel().getCamera();
                    camera.setViewCenter(camera.getViewCenter().x, camera.getViewCenter().y - 10, 0);
                } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    Camera camera = graphSwing.getViewPanel().getCamera();
                    camera.setViewCenter(camera.getViewCenter().x, camera.getViewCenter().y + 10, 0);
                }
            }
        });
    }

    public ViewPanel getViewPanel() {
        return viewPanel;
    }
}
