
public abstract class Gizmo {
	private int x, y;
	private Rotation rotation;

	public float getReflectionCoefficient() {
		return 1;
	}

	public void trigger() { }

	public void untrigger() { }

	public void tick() { }

	public Rotation getRotation() {
		return rotation;
	}
}
