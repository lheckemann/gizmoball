package gizmoball.model.gizmos;

import gizmoball.model.ReadModel;
import physics.LineSegment;

import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpinningFlipper extends Flipper {
    public SpinningFlipper(boolean isLeft) {
        super(isLeft);
    }

    @Override
    public void tick() {
        if(getType() == GizmoType.LEFT_SPINNING_FLIPPER)
            pivotAngle += (ANGULAR_VELOCITY * ReadModel.SECONDS_PER_TICK);
        else
            pivotAngle -= (ANGULAR_VELOCITY * ReadModel.SECONDS_PER_TICK);
    }

    @Override
    public GizmoType getType() {
        if (isLeftFlipper)
            return GizmoType.LEFT_SPINNING_FLIPPER;
        else
            return GizmoType.RIGHT_SPINNING_FLIPPER;
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform t = super.getTransform();
        t.translate(getWidth() / 2, getHeight() / 2);

        // Translate to pivot point
        t.rotate(-pivotAngle);
        // Translate back after rotation
        t.translate(-0.25, -0.25);
        return t;
    }

    @Override
    public Double getAngularVelocity() {
        return isLeftFlipper ? -ANGULAR_VELOCITY : ANGULAR_VELOCITY;
    }

    private static final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
            new LineSegment(0, 0.25, 0, 0.75),
            new LineSegment(0.5, 0.25, 0.5, 0.75)
    ).collect(Collectors.toSet()));
    @Override
    public Set<LineSegment> getLineSegments() {
        return lines;
    }

    private static final Set<physics.Circle> circles = Collections.unmodifiableSet(Stream.of(
            new physics.Circle(0.25, 0.25, 0.25),
            new physics.Circle(0.25, 0.75, 0.25),
            new physics.Circle(0, 0.25, 0.05),
            new physics.Circle(0, 0.75, 0.05),
            new physics.Circle(0.5, 0.25, 0.05),
            new physics.Circle(0.5, 0.75, 0.05)
    ).collect(Collectors.toSet()));
    @Override
    public Set<physics.Circle> getCircles() {
        return circles;
    }
}
