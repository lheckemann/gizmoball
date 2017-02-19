package gizmoball.model.gizmos;

import physics.*;
import physics.Circle;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Absorber extends Gizmo {
    private final int width;
    private final int height;

    public Absorber(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GizmoType getType() {
        return GizmoType.ABSORBER;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public Set<LineSegment> getLineSegments() {
        return Collections.unmodifiableSet(
            Stream.of(
                    new LineSegment(0, 0, 0, height),
                    new LineSegment(0, height, width, height),
                    new LineSegment(width, height, width, 0),
                    new LineSegment(width, 0, 0, 0)
            ).collect(Collectors.toSet())
        );
    }

    @Override
    public Set<Circle> getCircles() {
        // TODO
        return Collections.emptySet();
    }

    @Override
    public void rotate() {
    }
}
