package gizmoball.model;

import java.util.HashSet;
import java.util.Set;

public class SimpleObservable implements Observable {
    private Set<Observer> observers = new HashSet<>();
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void update() {
        observers.forEach(Observer::update);
    }
}
