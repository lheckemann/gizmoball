package gizmoball.model.gizmos;

import gizmoball.model.ReadModel;
import gizmoball.model.Ball;
import physics.*;
import physics.Circle;

import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Flipper extends Gizmo {
    private static final double ANGULAR_VELOCITY = 6d * Math.PI;
    private boolean isLeftFlipper;
    private boolean active;
    private static final double MAX_ANGLE = Math.PI / 2;
    private static final double MIN_ANGLE = 0;
    private double pivotAngle;

    public Flipper(boolean isLeft) {
        super();
        active = false;
        pivotAngle = 0d;
        isLeftFlipper = isLeft;
    }

    @Override
    public double getReflectionCoefficient() {
        // TODO take angular velocity into account? Is that possible?
        return 0.95f;
    }

    @Override
    public Ball trigger() {
        active = !active;
        return null;
    }

    @Override
    public void tick() {
        if (active && pivotAngle < MAX_ANGLE) {
            pivotAngle += (ANGULAR_VELOCITY * ReadModel.SECONDS_PER_TICK);
            pivotAngle = Math.min(MAX_ANGLE, pivotAngle);
        } else if (!active && pivotAngle > MIN_ANGLE) {
            pivotAngle -= (ANGULAR_VELOCITY * ReadModel.SECONDS_PER_TICK);
            pivotAngle = Math.max(MIN_ANGLE, pivotAngle);
        }
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
    public GizmoType getType() {
        if (isLeftFlipper)
            return GizmoType.LEFT_FLIPPER;
        else
            return GizmoType.RIGHT_FLIPPER;
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform t = super.getTransform();
        t.translate(getWidth() / 2, 0);
        if (getType().equals(GizmoType.RIGHT_FLIPPER)) {
            t.scale(-1, 1);
        }
        t.translate(-getWidth() / 2, 0);

        // Translate to pivot point
        t.translate(0.25, 0.25);
        t.rotate(-pivotAngle);
        // Translate back after rotation
        t.translate(-0.25, -0.25);
        return t;
    }

    @Override
    public Vect getPivot() {
        return new Vect(0.25, 0.25);
    }

    @Override
    public Double getAngularVelocity() {
        if (MIN_ANGLE < pivotAngle && pivotAngle < MAX_ANGLE) {
            return isLeftFlipper ? -ANGULAR_VELOCITY : ANGULAR_VELOCITY;
        }
        return 0.0;
    }

    private static final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
            new LineSegment(0, 0.25, 0, 1.75),
            new LineSegment(0.5, 0.25, 0.5, 1.75)
    ).collect(Collectors.toSet()));
    @Override
    public Set<LineSegment> getLineSegments() {
        return lines;
    }

    private static final Set<Circle> circles = Collections.unmodifiableSet(Stream.of(
            new Circle(0.25, 0.25, 0.25),
            new Circle(0.25, 1.75, 0.25)
    ).collect(Collectors.toSet()));
    @Override
    public Set<Circle> getCircles() {
        return circles;
    }
}
