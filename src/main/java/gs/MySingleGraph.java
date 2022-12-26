package gs;

import org.graphstream.graph.implementations.SingleGraph;

public class MySingleGraph extends SingleGraph {
    public MySingleGraph(String id) {
        super(id);
        setStrict(false);
        setAutoCreate(true);
        setAttribute("ui.quality");
        setAttribute("ui.antialias");
    }
}
