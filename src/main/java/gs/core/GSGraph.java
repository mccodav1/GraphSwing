package gs.core;

import org.graphstream.graph.implementations.SingleGraph;

public class GSGraph extends SingleGraph {
    public GSGraph(String id) {
        super(id);
        setStrict(false);
        setAutoCreate(true);
        setAttribute("ui.quality", 4); // 1 to 4
        setAttribute("ui.antialias");
        //setAttribute("layout.force", .1);
        setAttribute("layout.stabilization-limit", 1);

        //setAttribute("layout.weight", 50); //The force of repulsion of a node. The larger the value, the more the node repulses its neighbours. Set on node.
    }
}
