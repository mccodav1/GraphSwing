package gs.gui.leftpanel.nodepanels.sliderpanels;

import gs.core.GraphSwing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SliderPanel extends JPanel {

        private JSlider slider;

        private GraphSwing graphSwing;

        public SliderPanel() {
            setMaximumSize(new Dimension(300, 100));
            Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            setBorder(padding);
            slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(1);
            //slider.setPaintTicks(true);
            //slider.setPaintLabels(true);
            add(slider);
        }




    public JSlider getSlider() {
            return slider;
        }
}
