package gizmoball.model.gizmos;

import physics.LineSegment;

import java.util.Collections;
import java.util.Set;

public class Circle extends BaseGizmo {
    @Override
    public GizmoType getType() {
        return GizmoType.CIRCLE;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
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
