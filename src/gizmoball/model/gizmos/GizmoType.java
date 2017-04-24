/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
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
