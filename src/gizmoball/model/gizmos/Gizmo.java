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
     * Processes a tick spanning the given lapse.
     */
    void tick(double lapse);

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
