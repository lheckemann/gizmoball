package gizmoball.model;

public interface RunModel extends ReadModel {
    double SECONDS_PER_TICK = 1.0/60;

    void tick();

    void keyPressed(int code);

    void keyReleased(int code);
}
