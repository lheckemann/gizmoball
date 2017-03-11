package gizmoball.model.gizmos;

import gizmoball.model.Ball;

public class Sink extends Square {

    @Override
    public GizmoType getType() {
        return GizmoType.SINK;
    }

    @Override
    public Ball ballHit(Ball ball) {
        return null; // Remove the ball
    }
}
