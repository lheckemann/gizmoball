package model;

public interface BuildModel {
    void connectKeyPress(int code);
    void connectKeyRelease(int code);
    void select(double x, double y);
}
