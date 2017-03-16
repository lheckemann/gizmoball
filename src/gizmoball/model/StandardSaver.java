package gizmoball.model;

import java.util.stream.Collectors;

public class StandardSaver extends Saver {
    public StandardSaver(BuildModel model) {
        super(model);
        this.taggedGizmos = this.getTaggedGizmos(model.getGizmos()
                .stream()
                .filter(g -> g.getType().isSpecified())
                .collect(Collectors.toList()));
    }
}
