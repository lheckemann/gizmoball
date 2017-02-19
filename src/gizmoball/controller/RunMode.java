package gizmoball.controller;

public enum RunMode {
    RUN(0), STOP(1);
    private int value;

    RunMode(int value) {
        this.value = value;
    }
}