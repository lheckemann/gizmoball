package gizmoball.model.gizmos;

import gizmoball.model.Model;
import gizmoball.model.Ball;

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
    public int getPivotAngle() throws GizmoTypeException {
        return pivotAngle;
    }
}
