package gizmoball.model.gizmos;

import java.awt.geom.AffineTransform;
import java.util.*;

import physics.*;

import gizmoball.model.*;

public interface Gizmo extends ReadGizmo {
    /**
     * Rotates clockwise a quadrant.
     */
    public void rotate();

    /**
     * Sets the anchor position on the X axis.
     */
    public void setX(int x);

    /**
     * Sets the anchor position on the Y axis.
     */
    public void setY(int y);

    /**
     * Processes a tick.
     */
    public void tick();

    /**
     * Triggers the gizmo.
     * The returned ball, if any, is added to the game.
     */
    public Ball trigger();

    /**
     * Get the reflection coefficient for colliding balls.
     */
    public double getReflectionCoefficient();

    /**
     * Get the representing set of line segments.
     * The affine transform returned by `getTransform()` is applied to it.
     */
    public abstract Set<LineSegment> getLineSegments();

    /**
     * Get the representing set of circles.
     * The affine transform returned by `getTransform()` is applied to it.
     */
    public abstract Set<physics.Circle> getCircles();

    /**
     * Checks whether the given ball is inside the area delimited by the gizmo.
     */
    public boolean containsBall(Ball ball);

    /**
     * Hit the gizmo with a ball.
     * The given ball is removed from the game and given to the gizmo.
     * The returned ball, if any, will be added back to the game.
     */
    public Ball ballHit(Ball ball);

    /**
     * Get the point on which the gizmo pivots.
     */
    public Vect getPivot();

    /**
     * Get the angular velocity at which the gizmo pivots.
     */
    public Double getAngularVelocity();

    /**
     * Get the set of cells this gizmo occupies.
     */
    public Set<Vect> getCells();
}
