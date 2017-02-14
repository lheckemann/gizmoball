package gizmoball.controller;

public enum CurrentMode {
    BUILD(0), RUN(1);
    private int value;
    CurrentMode(int value) {
        this.value = value;
    }
}