package gizmoball.model;

import java.util.Map;
import java.util.Set;

import gizmoball.model.gizmos.ReadGizmo;

public interface BuildModel extends ReadModel {
    /**
     * Selects the ball or gizmo at the provided location as an operand in
     * future operations. Gizmos are selected by their bounding boxes.
     */
    void select(double x, double y);

    /**
     * Moves the selection to the given location.
     * If a gizmo is selected, the destination will be obtained from the integer
     * parts of the coordinates.
     * If there is no selection or there is no ball or gizmo at the selected
     * location, this will effectively be a noop.
     * TODO: moving outside of the arena (noop or a deletion?)
     * TODO: moving to an already occupied location
     * @throws PositionOverlapException
     * @throws PositionOutOfBoundsException
     */
    void move(double dX, double dY) throws PositionOverlapException, PositionOutOfBoundsException;

    /**
     * Deletes the current selection.
     * If there is no selection or there is no ball or gizmo at the selected
     * location, this will effectively be a noop.
     */
    void delete();

    // TODO
    void addAbsorber(int width, int height) throws PositionOverlapException, PositionOutOfBoundsException;

    // TODO
    void addSquare() throws PositionOverlapException, PositionOutOfBoundsException;

    // TODO
    void addCircle() throws PositionOverlapException, PositionOutOfBoundsException;

    // TODO
    void addTriangle() throws PositionOverlapException, PositionOutOfBoundsException;

    // TODO
    void addRightFlipper() throws PositionOverlapException, PositionOutOfBoundsException;

    // TODO
    void addLeftFlipper() throws PositionOverlapException, PositionOutOfBoundsException;

    /**
     * Rotates the gizmo at the selected location clockwise.
     * If there is no selection or the selected location is not a gizmo, this
     * will effectively be a noop.
     */
    void rotateGizmo();

    /**
     * Creates a new ball at the selected location.
     * If there is no selection or the selected location is already occupied by
     * some other item, this will effectively be a noop.
     * @throws PositionOutOfBoundsException
     */
    void addBall(double vX, double vY) throws PositionOverlapException, PositionOutOfBoundsException;

    /**
     * Sets the velocity of the ball at the selected location.
     * If not set, the default initial velocity for a ball is 50L/s.
     * If there is no selection or the selected location is not a ball, this
     * will effectively be a noop.
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
     * will effectively be a noop.
     */
    void triggerOnKeyPress(int key);

    /**
     * Connects key releases of the given key to the triggering of the currently
     * selected gizmo.
     * If there is no selection or the selected location is not a gizmo, this
     * will effectively be a noop.
     */
    void triggerOnKeyRelease(int key);

    // TODO
    void triggerOnOuterWalls();

    /***
     * Connects the bumping of the given Gizmo to the triggering of the currently
     * selected Gizmo
     */
    void triggerOnGizmo(ReadGizmo gizmo);

    /**
     * Resets all the state related to a particular game.
     * This includes all the gizmos, balls, connections and the gravity and
     * friction.
     */
    void reset();

    /***
     * Returns a mapping from a Gizmo to the Gizmos it trigger
     */
    Map<ReadGizmo, Set<ReadGizmo>> getGizmoToGizmoMap();

    /***
     * Returns a mapping from a key release to all of the Gizmos it triggers
     */
    Map<Integer, Set<ReadGizmo>> getKeyReleaseToGizmoMap();

    /***
     * Returns a mapping from a key press to all of the Gizmos it triggers
     */
    Map<Integer, Set<ReadGizmo>> getKeyPressToGizmoMap();

}
