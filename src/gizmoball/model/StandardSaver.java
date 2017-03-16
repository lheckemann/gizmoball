package gizmoball.model;

import java.util.*;
import java.util.stream.Collectors;

import gizmoball.model.gizmos.GizmoType;

public class StandardSaver extends Saver {
    protected static final Collection<GizmoType> standard = new HashSet<>();
    static {
        standard.add(GizmoType.ABSORBER);
        standard.add(GizmoType.SQUARE);
        standard.add(GizmoType.CIRCLE);
        standard.add(GizmoType.TRIANGLE);
        standard.add(GizmoType.RIGHT_FLIPPER);
        standard.add(GizmoType.LEFT_FLIPPER);
    }

    public StandardSaver(BuildModel model) {
        super(model);
        this.taggedGizmos = this.getTaggedGizmos(model.getGizmos()
                .stream()
                .filter(g -> standard.contains(g.getType()))
                .collect(Collectors.toList()));
    }
}
