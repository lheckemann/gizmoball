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

import physics.LineSegment;

import java.util.Collections;
import java.util.Set;

public class Circle extends BaseGizmo {
    @Override
    public GizmoType getType() {
        return GizmoType.CIRCLE;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    private physics.Circle physics = new physics.Circle(0.5, 0.5, 0.5);
    @Override
    public Set<physics.Circle> getCircles() {
        return Collections.singleton(physics);
    }

    @Override
    public Set<LineSegment> getLineSegments() {
        return Collections.emptySet();
    }
}
