package gs;

import gs.core.GraphController;
import gs.core.GraphModel;
import gs.view.GraphView;


public class Main {
    public static void main(String[] args) {
        // Use MVC pattern
        // Model: GraphSwing
        // View: gsGUI
        // Controller: gs.Main
        GraphModel model = new GraphModel();
        GraphView view = new GraphView();
        GraphController controller = new GraphController(model, view);
        controller.run();
    }
}
