

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

	@Override
	public void tick() {
		// 1080 degrees per second
		if (active && position < 90) {
			position += (1080 / Model.TICKS_PER_SECOND);
		}
		else if (!active && position > 0) {
			position -= (1080 / Model.TICKS_PER_SECOND);
		}
	}

	public int getPosition() {
		return position;
	}
}
