package gizmoball.model.gizmos;

import physics.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Flipper extends Gizmo {
    protected static final double ANGULAR_VELOCITY = 6d * Math.PI;
    protected boolean isLeftFlipper;
    protected double pivotAngle;

    protected Flipper(boolean isLeft) {
        pivotAngle = 0d;
        isLeftFlipper = isLeft;
    }

    @Override
    public double getReflectionCoefficient() {
        // TODO take angular velocity into account? Is that possible?
        return 0.95f;
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public Vect getPivot() {
        return new Vect(0.25, 0.25);
    }

    private static final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
            new LineSegment(0, 0.25, 0, 1.75),
            new LineSegment(0.5, 0.25, 0.5, 1.75)
    ).collect(Collectors.toSet()));
    @Override
    public Set<LineSegment> getLineSegments() {
        return lines;
    }

    private static final Set<physics.Circle> circles = Collections.unmodifiableSet(Stream.of(
            new physics.Circle(0.25, 0.25, 0.25),
            new physics.Circle(0.25, 1.75, 0.25),
            new physics.Circle(0, 0.25, 0.05),
            new physics.Circle(0, 1.75, 0.05),
            new physics.Circle(0.5, 0.25, 0.05),
            new physics.Circle(0.5, 1.75, 0.05)
    ).collect(Collectors.toSet()));
    @Override
    public Set<physics.Circle> getCircles() {
        return circles;
    }
}
