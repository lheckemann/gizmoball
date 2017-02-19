package gizmoball.model.gizmos;

public class Absorber extends Gizmo {
    private final int width;
    private final int height;

    public Absorber(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GizmoType getType() {
        return GizmoType.ABSORBER;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public void rotate() {
    }
}
