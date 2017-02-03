package gizmoball.model;

public interface ReadGizmo {
    public static enum Rotation {
        N, E, S, W;
    }
    public static enum GizmoType {
        ABSORBER, SQUARE, CIRCLE, TRIANGLE, RIGHT_FLIPPER, LEFT_FLIPPER;
    }

    public GizmoType getType();
    public Rotation getRotation();
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
}
