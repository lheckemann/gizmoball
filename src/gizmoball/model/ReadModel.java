package gizmoball.model;

import java.util.Observer;
import java.util.Set;

import physics.Vect;

public interface ReadModel {
    public Set<Gizmo> getGizmos();
    public Set<Vect> getBallPositions(); // FIXME: we don't want to use vect here

    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
}
