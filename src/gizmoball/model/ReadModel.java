package gizmoball.model;

import java.util.Set;

import gizmoball.model.gizmos.ReadGizmo;

public interface ReadModel {
    double SECONDS_PER_TICK = 1.0/30;

    // TODO docs
    Set<ReadGizmo> getGizmos();

    // TODO docs
    Set<ReadBall> getBalls();

    int getWidth();
    int getHeight();
}
