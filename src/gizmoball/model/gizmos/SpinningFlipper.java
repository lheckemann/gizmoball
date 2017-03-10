package gizmoball.model.gizmos;

import gizmoball.model.ReadModel;

import java.awt.geom.AffineTransform;

public class SpinningFlipper extends Flipper {
    public SpinningFlipper(boolean isLeft) {
        super(isLeft);
    }

    @Override
    public void tick() {
        pivotAngle += (ANGULAR_VELOCITY * ReadModel.SECONDS_PER_TICK);
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
}
