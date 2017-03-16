package gizmoball.model.gizmos;

import java.awt.geom.AffineTransform;

import physics.*;

import gizmoball.model.*;

public interface Gizmo extends ReadGizmo {
    /**
     * Rotates clockwise a quadrant.
     * If the gizmo cannot be rotated, then an exception will be thrown
     * @throws NonRotatableException 
     */
    void rotate() throws NonRotatableException;

    /**
     * Sets the anchor position on the X axis.
     */
    void setX(int x);

    /**
     * Sets the anchor position on the Y axis.
     */
    void setY(int y);

    /**
     * Processes a tick.
     */
    void tick();

    /**
     * Triggers the gizmo.
     * The returned ball, if any, is added to the game.
     */
    Ball trigger();

    /**
     * Hit the gizmo with a ball.
     * The given ball is removed from the game and given to the gizmo.
     * The returned ball, if any, will be added back to the game.
     */
    Ball ballHit(Ball ball);
}
