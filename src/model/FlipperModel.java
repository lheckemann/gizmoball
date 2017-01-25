package model;

public class FlipperModel extends Gizmo {

    private boolean isLeftFlipper;

    private boolean active;

    // Between 0 and 90, indicates how far along the animation it is (current
    // angle)
    // 0 = at rest
    // in between = in animation
    // 90 = active
    private int position;

    public boolean isActive() {
        return active;
    }

    public boolean isLeft() {
        return isLeftFlipper;
    }

    public FlipperModel(boolean isLeft) {
        super();
        active = false;
        position = 0;
        isLeftFlipper = isLeft;
    }

    @Override
    public float getReflectionCoefficient() {
        // TODO take angular velocity into account? Is that possible?
        return 0.95f;
    }

    @Override
    public void trigger() {
        active = true;
    }

    @Override
    public void untrigger() {
        active = false;
    }

    private static final int ROTATION_SPEED = 1080; // Degrees per second
    @Override
    public void tick() {
        if (active && position < 90) {
            position += (ROTATION_SPEED / Model.TICKS_PER_SECOND);
            position = Math.min(90, position);
        } else if (!active && position > 0) {
            position -= (ROTATION_SPEED/ Model.TICKS_PER_SECOND);
            position = Math.max(0, position);
        }
    }

    public int getPosition() {
        return position;
    }
}
