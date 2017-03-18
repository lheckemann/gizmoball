package gizmoball.model;

import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.NonRotatableException;
import gizmoball.model.gizmos.ReadGizmo;

public interface BuildModel extends ReadModel {
    /**
     * Set the ball or gizmo at the provided location as an operand for future
     * operations.
     * Gizmos are selected by their bounding boxes.
     * Balls are selected by their occupied surface.
     */
    void select(double x, double y);

    /**
     * Moves the selected element to the given location.
     * Gizmos are selected by their bounding boxes. The cell that contains the
     * given destination is their new anchor point.
     * If there is no selection or there is no ball or gizmo at the selected
     * location, this is effectively a noop.
     * @throws PositionOverlapException if an elements destination location is
     * occupied by another element.
     * @throws PositionOutOfBoundsException if the destination is outside the
     * arena.
     */
    void move(double dX, double dY) throws PositionOverlapException, PositionOutOfBoundsException;

    /**
     * Deletes the current selection.
     * If there is no selection or there is no ball or gizmo at the selected
     * location, this will effectively be a noop.
     */
    void delete();

    /**
     * Adds the given gizmo to the selected location.
     * @throws PositionOverlapException if the selected location is occupied by
     * another element.
     * @throws PositionOutOfBoundsException if the selected location is outside
     * of the arena.
     */
    void addGizmo(Gizmo gizmo) throws PositionOverlapException, PositionOutOfBoundsException;

    /**
     * Rotates the gizmo at the selected location clockwise.
     * If there is no selection or the selected location is not a gizmo, this
     * is effectively a noop.
     * If the gizmo cannot be rotated, then an exception is thrown
     * @throws NonRotatableException
     */
    void rotateGizmo() throws NonRotatableException;

    /**
     * Creates a new ball at the selected location.
     * If there is no selection this is effectively a noop.
     * @throws PositionOutOfBoundsException if the selected location is occupied
     * by another element.
     * @throws PositionOutOfBoundsException if the selected location is outside
     * of the arena.
     */
    void addBall(double vX, double vY) throws PositionOverlapException, PositionOutOfBoundsException;

    /**
     * Sets the velocity of the ball at the selected location.
     * If not set, the default initial velocity for a ball is 50L/s.
     * If there is no selection or the selected location is not a ball, this
     * is effectively a noop.
     */
    void setBallVelocity(double vX, double vY);

    /**
     * Gets the global gravity constant.
     * If not set, the default global gravity is 25L/s.
     */
    double getGravity();

    /**
     * Sets the global gravity constant.
     * If not set, the default global gravity is 25L/s.
     */
    void setGravity(double gravity);

    /**
     * Gets the global frictional constant μ (mu).
     * v_new = v_old * (1 - μ*Δt - μ₂*Δt*|v_old|)
     * If not set, the default value for μ is 0.025/s.
     */
    double getFrictionMu();

    /**
     * Gets the global frictional constant μ₂ (mu2).
     * v_new = v_old * (1 - μ*Δt - μ₂*Δt*|v_old|)
     * If not set, the default value for μ₂ is 0.025/L.
     */
    double getFrictionMu2();

    /**
     * Sets the global frictional constants μ (mu) and μ₂ (mu2).
     * If not set, the default value for μ is 0.025/s and the default value for
     * μ₂ is 0.025/s.
     */
    void setFriction(double mu, double mu2);

    /**
     * Connects key presses of the given key to the triggering of the currently
     * selected gizmo.
     * If there is no selection or the selected location is not a gizmo, this
     * is effectively a noop.
     */
    void triggerOnKeyPress(int key);

    /**
     * Connects key releases of the given key to the triggering of the currently
     * selected gizmo.
     * If there is no selection or the selected location is not a gizmo, this
     * is effectively a noop.
     */
    void triggerOnKeyRelease(int key);

    /**
     * Connects ball hits on the outer walls to the triggering of the currently
     * selected gizmo.
     * If there is no selection or the selected location is not a gizmo, this
     * is effectively a noop.
     */
    void triggerOnOuterWalls();

    /**
     * Connects ball hits on the given gizmo to the triggering of the currently
     * selected gizmo.
     * If there is no selection, the selected location is not a gizmo, or the
     * given gizmo is not in the model, this is effectively a noop.
     */
    void triggerOnGizmo(ReadGizmo gizmo);

    /**
     * Connects ball hits on the gizmo at the given location to the triggering
     * of the currently selected gizmo.
     * If there is no selection, the selected location is not a gizmo, or the
     * given location is not a gizmo, this is effectively a noop.
     */
    void triggerOnGizmoAt(double x, double y);

    /**
     * Checks if there is some element at the given location.
     */
    boolean notEmpty(double x, double y);

    /**
     * Resets all the state related to a particular game.
     * This includes all the gizmos, balls, connections and the gravity and
     * friction.
     */
    void reset();
}
