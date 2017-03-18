package gizmoball.model.gizmos;

import gizmoball.model.Ball;
import gizmoball.model.Geometry;
import gizmoball.model.RunModel;
import physics.LineSegment;
import physics.Vect;

import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StandardFlipper extends Flipper {
    private boolean active;
    private static final double MAX_ANGLE = Math.PI / 2;
    private static final double MIN_ANGLE = 0;

    public StandardFlipper(boolean isLeft) {
        super(isLeft);
        active = false;
    }

    @Override
    public Ball trigger() {
        active = !active;
        return null;
    }

    @Override
    public void tick() {
        if (active && pivotAngle < MAX_ANGLE) {
            pivotAngle += (ANGULAR_VELOCITY * RunModel.SECONDS_PER_TICK);
            pivotAngle = Math.min(MAX_ANGLE, pivotAngle);
        } else if (!active && pivotAngle > MIN_ANGLE) {
            pivotAngle -= (ANGULAR_VELOCITY * RunModel.SECONDS_PER_TICK);
            pivotAngle = Math.max(MIN_ANGLE, pivotAngle);
        }
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

    @Override
    public Vect getPivot() {
        return Geometry.transformThrough(getTransform(), new Vect(0.25, 0.25));
    }
}
