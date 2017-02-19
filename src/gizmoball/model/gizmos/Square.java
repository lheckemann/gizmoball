package gizmoball.model.gizmos;

import physics.*;
import physics.Circle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Square extends Gizmo {
    public GizmoType getType() {
        return GizmoType.SQUARE;
    }

    public int getWidth() {
        return 1;
    }

    public int getHeight() {
        return 1;
    }

    private static final Set<LineSegment> lines = Collections.unmodifiableSet(
        Stream.of(new LineSegment(0, 0, 0, 1),
                new LineSegment(0, 1, 1, 1),
                new LineSegment(1, 1, 1, 0),
                new LineSegment(1, 0, 0, 0)).collect(Collectors.toSet())
    );
    @Override
    protected Set<LineSegment> getBasicLineSegments() {
        return lines;
    }

    private static final double circleSize = 0.2;
    private static final Set<Circle> circles = Collections.unmodifiableSet(
            Stream.of(new Circle(circleSize, circleSize, circleSize),
                    new Circle(circleSize, 1-circleSize, circleSize),
                    new Circle(1-circleSize, 1-circleSize, circleSize),
                    new Circle(1-circleSize, circleSize, circleSize)).collect(Collectors.toSet())
    );
    @Override
    protected Set<Circle> getBasicCircles() {
        return circles;
    }
}
