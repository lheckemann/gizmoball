package gizmoball.model.gizmos;

import gizmoball.model.Model;
import gizmoball.model.Ball;
import physics.*;
import physics.Circle;

import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Flipper extends Gizmo {

    private boolean isLeftFlipper;

    private boolean active;

    // Between 0 and 90, indicates how far along the animation it is (current
    // angle)
    // 0 = at rest
    // in between = in animation
    // 90 = active
    private int pivotAngle;

    public Flipper(boolean isLeft) {
        super();
        active = false;
        pivotAngle = 0;
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

    private static final int ROTATION_SPEED = 1080; // Degrees per second

    @Override
    public void tick() {

        if (active && pivotAngle < 90) {
            pivotAngle += (ROTATION_SPEED / Model.TICKS_PER_SECOND);
            pivotAngle = Math.min(90, pivotAngle);
        } else if (!active && pivotAngle > 0) {
            pivotAngle -= (ROTATION_SPEED / Model.TICKS_PER_SECOND);
            pivotAngle = Math.max(0, pivotAngle);
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
        AffineTransform posAndRot = super.getTransform();
        AffineTransform pivot = new AffineTransform();
        pivot.translate(getWidth() / 2, 0);
        if (getType().equals(GizmoType.RIGHT_FLIPPER)) {
            pivot.scale(-1, 1);
        }
        pivot.translate(-getWidth() / 2, 0);

        // Translate to pivot point
        pivot.translate(0.25, 0.25);
        pivot.rotate(-Math.toRadians(pivotAngle));
        // Translate back after rotation
        pivot.translate(-0.25, -0.25);
        posAndRot.concatenate(pivot);
        return posAndRot;
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
            new Circle(0.25, 0.25, 0.5),
            new Circle(0.25, 1.75, 0.5)
    ).collect(Collectors.toSet()));
    @Override
    public Set<Circle> getCircles() {
        return circles;
    }
}
