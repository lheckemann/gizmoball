package gizmoball.model.gizmos;

import java.awt.geom.AffineTransform;
import java.util.*;

import physics.*;

import gizmoball.model.*;

public interface Gizmo extends ReadGizmo {
    // TODO
    public void rotate();

    // TODO
    public void setX(int x);

    // TODO
    public void setY(int y);

    // TODO
    public void tick();

    // TODO
    public Ball trigger();

    // TODO
    public double getReflectionCoefficient();

    // TODO
    public abstract Set<LineSegment> getLineSegments();

    // TODO
    public abstract Set<physics.Circle> getCircles();

    // TODO
    public boolean containsBall(Ball ball);

    // TODO
    public Ball ballHit(Ball ball);

    // TODO
    public Vect getPivot();

    // TODO
    public Double getAngularVelocity();

    // TODO
    public Set<Vect> getCells();
}
