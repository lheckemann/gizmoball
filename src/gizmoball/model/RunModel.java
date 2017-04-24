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

public interface RunModel extends ReadModel {
    double SECONDS_PER_TICK = 1.0/60;

    /**
     * Recomputes each gizmo's and each ball's state.
     * Returns the amount of time processed.
     */
    double tick();

    /**
     * Triggers all gizmos listening to the given key press.
     */
    void keyPressed(int code);

    /**
     * Triggers all gizmos listening to the given key release.
     */
    void keyReleased(int code);
}
