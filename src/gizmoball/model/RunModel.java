package gizmoball.model;

public interface RunModel extends IModel {
    void tick();

    void keyPressed(int code);

    void keyReleased(int code);
}
