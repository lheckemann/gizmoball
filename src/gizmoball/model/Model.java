package gizmoball.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.stream.Collectors;

import physics.Vect;

public class Model implements BuildModel {
    private final double width;
    private final double height;
    private Map<String, Gizmo> gizmos;
    private Map<String, Ball> balls;
    private Map<Integer, Set<Gizmo>> keyPressMap;
    private Map<Integer, Set<Gizmo>> keyReleaseMap;
    private Map<Gizmo, Set<Gizmo>> gizmoMap;
    private Set<Gizmo> wallTriggers;
    private Set<Observer> observers;

    public Model(double width, double height) {
        this.width = width;
        this.height = height;
        this.reset();
        this.observers = new HashSet<>();
    }

    public void reset() {
        this.gizmos = new HashMap<>();
        this.balls = new HashMap<>();
        this.keyPressMap = new HashMap<>();
        this.keyReleaseMap = new HashMap<>();
        this.gizmoMap = new HashMap<>();
        this.wallTriggers = new HashSet<>();
    }

    public void select(double x, double y) {
    }

    public void move(double dX, double dY) {
    }

    public void delete() {
    }

    public void addGizmo(Gizmo.GizmoType type) {
    }

    public void rotateGizmo() {
    }

    public void addBall() {
    }

    public void setBallVelocity(double vX, double vY) {
    }

    public void getGravity() {
    }

    public void setGravity(double gravity) {
    }

    public void getFrictionMu() {
    }

    public void getFrictionMu2() {
    }

    public void setFriction(double mu, double mu2) {
    }

    public void connectKeyPress(int key) {
    }

    public void connectKeyRelease(int key) {
    }

    public void connectItems(double dX, double dY) {
    }

    public void load(InputStream input) {
    }

    public OutputStream save() {
        return null;  // TODO
    }

    public Set<Gizmo> getGizmos() {
        return new HashSet<>(this.gizmos.values());
    }

    public Set<Vect> getBallPositions() { // FIXME: we don't want to use vect here
        return this.balls.values()
                         .stream()
                         .map(b -> new Vect(b.getX(), b.getY()))
                         .collect(Collectors.toSet());
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        this.observers.remove(observer);
    }
}
