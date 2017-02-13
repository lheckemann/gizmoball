package model;

import java.util.Observer;
import java.util.Set;

public interface ReadModel {
    Set<ReadGizmo> getGizmos();
    void addObserver(Observer target);
    void removeObserver(Observer target);
}
