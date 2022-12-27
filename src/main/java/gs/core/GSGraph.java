package gs.core;

import org.graphstream.graph.implementations.SingleGraph;

public class GSGraph extends SingleGraph {
    public GSGraph(String id) {
        super(id);
        setStrict(false);
        setAutoCreate(true);
        setAttribute("ui.quality");
        setAttribute("ui.antialias");
    }
}
