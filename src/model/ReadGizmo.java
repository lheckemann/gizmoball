package model;

public interface ReadGizmo {
    Rotation getRotation();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    GizmoType getType();
    int getPivotAngle() throws GizmoTypeException;
}
