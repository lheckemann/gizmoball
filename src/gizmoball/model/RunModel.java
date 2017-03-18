package gizmoball.model;

public interface RunModel extends ReadModel {
    double SECONDS_PER_TICK = 1.0/60;

    /**
     * Recomputes each gizmo's and each ball's state.
     * - Notifies every gizmo a tick has happened.
     * - Processes ball collisions against gizmos, other balls, and outer walls.
     * - Applies gravity and friction to all balls.
     */
    void tick();

    /**
     * Triggers all gizmos listening to the given key press.
     */
    void keyPressed(int code);

    /**
     * Triggers all gizmos listening to the given key release.
     */
    void keyReleased(int code);
}
