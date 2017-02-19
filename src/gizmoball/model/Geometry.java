package gizmoball.model;

import physics.Circle;
import physics.LineSegment;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public abstract class Geometry {
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
        // NOTE: an AffineTransform will not necessarily preserve the circle-ness of a circle;
        // As an approximation, we take the average of the X and Y scaling factor as a scaling
        // factor for the radius.
        double newRadius = src.getRadius() * (t.getScaleX() + t.getScaleY()) / 2;
        return new Circle(dest, newRadius);
    }
}
