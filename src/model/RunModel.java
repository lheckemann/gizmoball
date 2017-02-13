package model;

public interface RunModel extends ReadModel {
    void tick();
    void keyPressed(int code);
    void keyReleased(int code);
}
