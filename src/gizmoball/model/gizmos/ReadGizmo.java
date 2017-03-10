package gizmoball.model.gizmos;

import java.awt.geom.AffineTransform;

public interface ReadGizmo {

    enum GizmoType {
        ABSORBER, SQUARE, CIRCLE, TRIANGLE, RIGHT_FLIPPER, LEFT_FLIPPER, SPAWNER, SINK;

        public String toString() {
            return super.toString().replace("_", " ").toLowerCase();
        }
    }

    GizmoType getType();
    Rotation getRotation();
    int getWidth();
    int getHeight();
    int getX();
    int getY();
    boolean equals(Object obj);
    AffineTransform getTransform();
}
