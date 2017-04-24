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

import physics.Circle;
import physics.LineSegment;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle extends BaseGizmo {
    @Override
    public GizmoType getType() {
        return GizmoType.TRIANGLE;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    private static final Set<LineSegment> lines = Collections.unmodifiableSet(
            Stream.of(new LineSegment(0, 0, 0, 1),
                    new LineSegment(0, 1, 1, 0),
                    new LineSegment(1, 0, 0, 0)).collect(Collectors.toSet())
    );
    @Override
    public Set<LineSegment> getLineSegments() {
        return lines;
    }

    private static final double circleOffset = 0.1;
    private static final double circleSize = 0.09;
    private static final Set<Circle> circles = Collections.unmodifiableSet(
            Stream.of(new Circle(circleOffset, 2*circleOffset, circleSize),
                    new Circle(circleOffset, 1-circleOffset, circleSize),
                    new Circle(1-2*circleOffset, circleOffset, circleSize)).collect(Collectors.toSet())
    );
    @Override
    public Set<Circle> getCircles() {
        return circles;
    }
}
