package gizmoball.model.gizmos;

import java.awt.geom.AffineTransform;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ReadGizmo {

    enum GizmoType {
        ABSORBER, SQUARE, CIRCLE, TRIANGLE,
        RIGHT_FLIPPER, RIGHT_SPINNING_FLIPPER, LEFT_FLIPPER, LEFT_SPINNING_FLIPPER,
        SPAWNER, SINK;

        public String toString() {
            return super.toString().replace("_", " ").toLowerCase();
        }

        public String saveName() {
            boolean uppercaseNext = true;
            StringBuilder out = new StringBuilder();
            for (char c : super.toString().toCharArray()) {
                if (c == '_') {
                    uppercaseNext = true;
                    continue;
                }
                if (uppercaseNext) {
                    out.append(Character.toUpperCase(c));
                    uppercaseNext = false;
                } else {
                    out.append(Character.toLowerCase(c));
                }
            }
            return out.toString();
        }

        private static final Set<GizmoType> specified = Stream.of(
                ABSORBER, SQUARE, CIRCLE, TRIANGLE, RIGHT_FLIPPER, LEFT_FLIPPER
        ).collect(Collectors.toSet());

        public boolean isSpecified() {
            return specified.contains(this);
        }
    }

    /**
     * Returns the type of gizmo.
     * Mainly used for loading and saving purposes.
     */
    GizmoType getType();

    /**
     * Returns the current rotation.
     */
    Rotation getRotation();

    /**
     * Returns the current width.
     */
    int getWidth();

    /**
     * Returns the current height.
     */
    int getHeight();

    /**
     * Returns the current location on the X axis.
     */
    int getX();

    /**
     * Returns the current location on the Y axis.
     */
    int getY();

    /**
     * Returns the transform that will correctly place the gizmo in the arena.
     * This transform is used both for collision detection and for drawing
     * purposes. The transform is responsible for correctly placing and rotating
     * the gizmo. No further transforms will be applied to the gizmo.
     */
    AffineTransform getTransform();

    boolean equals(Object obj);
}
