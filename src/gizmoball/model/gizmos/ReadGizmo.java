package gizmoball.model.gizmos;

public interface ReadGizmo {

    enum GizmoType {
        ABSORBER, SQUARE, CIRCLE, TRIANGLE, RIGHT_FLIPPER, LEFT_FLIPPER
    }

    GizmoType getType();
    Rotation getRotation();
    int getWidth();
    int getHeight();
    int getPivotAngle() throws GizmoTypeException;
}
