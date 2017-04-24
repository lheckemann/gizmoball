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

import java.util.Set;
import java.util.Map;

import gizmoball.model.gizmos.ReadGizmo;

public interface ReadModel {
    /**
     * Returns the set of gizmos currently in the model.
     */
    Set<ReadGizmo> getGizmos();

    /**
     * Returns the set of balls currently managed by the model.
     * Balls that are being handled by a gizmo are not considered to be managed
     * by the model.
     */
    Set<ReadBall> getBalls();

    /**
     * Returns the width of the arena.
     */
    int getWidth();

    /**
     * Returns the height of the arena.
     */
    int getHeight();

    /**
     * Returns a map from a gizmo to the gizmos it triggers.
     */
    Map<ReadGizmo, Set<ReadGizmo>> getGizmoToGizmoMap();

    /**
     * Returns a map from a key release to the gizmos it triggers.
     */
    Map<Integer, Set<ReadGizmo>> getKeyReleaseToGizmoMap();

    /**
     * Returns a map from a key press to the gizmos it triggers.
     */
    Map<Integer, Set<ReadGizmo>> getKeyPressToGizmoMap();
    
    /**
     * Returns a set of all of the gizmos the Outerwalls trigger
     * @return
     */
    Set<ReadGizmo> getOuterwallTriggeredGizmos();
}
