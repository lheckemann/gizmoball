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
package gizmoball.model;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;


public abstract class Geometry {
    public static Vect transformThrough(AffineTransform t, Vect v) {
        double points[] = {v.x(), v.y()};
        t.transform(points, 0, points, 0, 1);
        return new Vect(points[0], points[1]);
    }

    public static LineSegment transformThrough(AffineTransform t, LineSegment src) {
        double points[] = {
                src.p1().x(),
                src.p1().y(),
                src.p2().x(),
                src.p2().y()
        };
        t.transform(points, 0, points, 0, 2);
        return new LineSegment(points[0], points[1], points[2], points[3]);
    }

    public static Circle transformThrough(AffineTransform t, Circle src) {
        Point2D dest = new Point2D.Double();
        t.transform(src.getCenter().toPoint2D(), dest);
        // NOTE: an AffineTransform will not necessarily preserve the circle-ness of a circle.
        // Because of this, we just leave the shape and size unmodified, which is terrible.
        return new Circle(dest, src.getRadius());
    }

    public static boolean circlesIntersect(Circle a, Circle b) {
        double radiusSum = a.getRadius() + b.getRadius();
        double distanceSquared = a.getCenter().distanceSquared(b.getCenter());
        return radiusSum * radiusSum >= distanceSquared;
    }
}
