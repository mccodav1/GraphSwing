package gs.core;

import gs.view.GraphView;
import gs.popups.OpenFilePopup;


public class GraphController {

    private GraphModel model;
    private GraphView view;
    public GraphController(GraphModel model, GraphView view) {
        this.model = model;
        this.view = view;
    }


    public void run(){
        view.redraw(model.getGraph());
        view.setVisible(true);
        addActionListeners();
    }
    private void addActionListeners() {
        view.getClearButton().addActionListener(e -> {
            view.clearNodeText();
        });

        view.getAddButton().addActionListener(e -> {
            String[] nodesToAdd = view.getNodesToAdd();
            if (nodesToAdd != null) {
                String[] connectedNodes = view.getConnectedNodesToAdd();
                for (String node : nodesToAdd) {
                    for (String connectedNode : connectedNodes) {
                        model.addEdge(node + connectedNode, node, connectedNode).addAttribute("length", 1);
                    }
                    model.setNodeAttribute("label", node);
                }
                view.redraw(model.getGraph());
            }
            view.clearNodeText();
        });

        view.getRemoveNodesButton().addActionListener(e -> {
            while (model.getNodeCount() > 0) {
                model.removeNode(0);
            }
        });

        view.getOpenButton().addActionListener(e -> {
            OpenFilePopup openFilePopup = new OpenFilePopup(model);
            openFilePopup.setVisible(true);
            // need to generate graph but also redraw after files opened
            openFilePopup.getOpenButton().addActionListener(f -> {
                openFilePopup.dispose();
                model.generateGraphFromFiles(openFilePopup.getNodeFile(), openFilePopup.getLinkFile());
                System.out.println(openFilePopup.getNodeFile());
                System.out.println(openFilePopup.getLinkFile());
                view.redraw(model.getGraph());
            });
        });

        view.getSaveButton().addActionListener(e -> {
            model.saveFile();
        });

        view.getRandomButton().addActionListener(e -> {
            model.addRandomNodes();
        });

        view.getAddLabelsButton().addActionListener(e -> {
            model.setShowLabels(true);
        });

        view.getRemoveLabelsButton().addActionListener(e -> {
            model.setShowLabels(false);
        });

        view.getCentralityButton().addActionListener(e->{
            throw new UnsupportedOperationException("Not supported yet.");
        });
    }

}
