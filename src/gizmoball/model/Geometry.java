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
        // NOTE: an AffineTransform will not necessarily preserve the circle-ness of a circle;
        // As an approximation, we take the average of the X and Y scaling factor as a scaling
        // factor for the radius.
        double newRadius = src.getRadius() * (Math.abs(t.getScaleX()) + Math.abs(t.getScaleY())) / 2;
        return new Circle(dest, newRadius);
    }
}
