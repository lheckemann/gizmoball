package gizmoball.model;

import java.util.Set;

import gizmoball.model.gizmos.ReadGizmo;

public interface ReadModel {
    int TICKS_PER_SECOND = 30;

    // TODO docs
    Set<ReadGizmo> getGizmos();

    // TODO docs
    Set<ReadBall> getBalls();

    Set<ReadBall> getBuildtimeBalls();

    int getWidth();
    int getHeight();
}
