package gizmoball.model.gizmos;

import physics.LineSegment;

import java.util.Collections;
import java.util.Set;

public class Circle extends Gizmo {
    public GizmoType getType() {
        return GizmoType.CIRCLE;
    }

    public int getWidth() {
        return 1;
    }

    public int getHeight() {
        return 1;
    }

    private physics.Circle physics = new physics.Circle(0.5, 0.5, 0.5);
    @Override
    public Set<physics.Circle> getCircles() {
        return Collections.singleton(physics);
    }

    @Override
    public Set<LineSegment> getLineSegments() {
        return Collections.emptySet();
    }
}
