package gizmoball.model;

import java.util.Set;
import java.util.Map;

import gizmoball.model.gizmos.ReadGizmo;

public interface ReadModel {
    /**
     * Return the gizmo at the specified location or null if none.
     */
    ReadGizmo getGizmoAt(int x, int y);

    /**
     * Return the ball at the specified location or null if none.
     */
    ReadBall getBallAt(double x, double y);

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
     * Returns a set of all of the gizmos the outer walls trigger.
     */
    Set<ReadGizmo> getOuterwallTriggeredGizmos();
}
