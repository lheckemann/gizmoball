package gizmoball.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import physics.Vect;

import gizmoball.model.gizmos.ReadGizmo;

public interface ReadModel {
    int TICKS_PER_SECOND = 30;
    int L_TO_PIXELS = 32;

    Collection<ReadGizmo> getGizmos();

    Set<Vect> getBallPositions(); // FIXME: we don't want to use vect here
    
    Map<Vect, ReadGizmo> getPositionToGizmoMap();
    
    Map<Vect, Set<ReadBall>> getPositionToBallMap();
}
