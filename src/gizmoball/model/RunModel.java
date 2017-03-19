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
