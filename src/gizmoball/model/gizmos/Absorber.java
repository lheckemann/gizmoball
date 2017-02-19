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
    private final Set<LineSegment> lines;
    private final Set<Circle> circles;

    public Absorber(int width, int height) {
        this.width = width;
        this.height = height;
        lines = Collections.unmodifiableSet(
            Stream.of(
                    new LineSegment(0, 0, 0, height),
                    new LineSegment(0, height, width, height),
                    new LineSegment(width, height, width, 0),
                    new LineSegment(width, 0, 0, 0)
            ).collect(Collectors.toSet())
        );
        // TODO
        circles = Collections.emptySet();
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
    protected Set<LineSegment> getBasicLineSegments() {
        return lines;
    }

    @Override
    protected Set<Circle> getBasicCircles() {
        return circles;
    }

    @Override
    public void rotate() {
    }
}
