package gizmoball.model;

import java.util.Set;

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
}
