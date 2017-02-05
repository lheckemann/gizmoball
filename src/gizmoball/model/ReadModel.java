package gizmoball.model;

import java.util.Observer;
import java.util.Set;

import physics.Vect;

import gizmoball.model.gizmos.ReadGizmo;

public interface ReadModel {
    public Set<ReadGizmo> getGizmos();
    public Set<Vect> getBallPositions(); // FIXME: we don't want to use vect here

    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
}
