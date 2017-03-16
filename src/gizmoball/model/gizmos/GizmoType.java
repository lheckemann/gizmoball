package gizmoball.model.gizmos;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GizmoType {
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
