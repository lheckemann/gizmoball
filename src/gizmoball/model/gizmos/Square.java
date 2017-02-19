package gizmoball.model.gizmos;

public class Square extends Gizmo {
    public GizmoType getType() {
        return GizmoType.SQUARE;
    }

    public int getWidth() {
        return 1;
    }

    public int getHeight() {
        return 1;
    }
}
