package gs.core;

import org.graphstream.graph.implementations.SingleGraph;

public class gsGraph extends SingleGraph {
    public gsGraph(String id) {
        super(id);
        setStrict(false);
        setAutoCreate(true);
        setAttribute("ui.quality");
        setAttribute("ui.antialias");
    }
}
