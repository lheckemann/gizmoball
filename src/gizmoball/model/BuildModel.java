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
     * Returns the currently selected gizmo, if any.
     */
    Gizmo getSelectedGizmo();

    /**
     * Returns the currently selected ball, if any.
     */
    Ball getSelectedBall();

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
     * Puts the given ball at the selected location.
     * @throws PositionOutOfBoundsException if the selected location is occupied
     * by another element.
     * @throws PositionOutOfBoundsException if the selected location is outside
     * of the arena.
     */
    void addBall(Ball ball) throws PositionOverlapException, PositionOutOfBoundsException;

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
     * Resets all the state related to a particular game.
     * This includes all the gizmos, balls, connections and the gravity and
     * friction.
     */
    void reset();
}
