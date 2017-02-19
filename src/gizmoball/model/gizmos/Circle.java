package gizmoball.model.gizmos;

public class Circle extends Gizmo {
    public GizmoType getType() {
        return GizmoType.CIRCLE;
    }

    public int getWidth() {
        return 1;
    }

    public int getHeight() {
        return 1;
    }
}
