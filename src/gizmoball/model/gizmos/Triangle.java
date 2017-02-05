package gizmoball.model.gizmos;

public class Triangle extends Gizmo {
    public GizmoType getType() {
        return GizmoType.TRIANGLE;
    }

    public int getWidth() {
        return 1;
    }

    public int getHeight() {
        return 1;
    }
}
