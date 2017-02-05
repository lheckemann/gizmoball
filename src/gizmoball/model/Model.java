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

import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;

public class Model implements BuildModel {
    private final double width;
    private final double height;
    private double selX;
    private double selY;
    private double gravity = 25;
    private double mu = 0.025;
    private double mu2 = 0.025;

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

    private Gizmo getGizmoAt(double x, double y) {
        return this.gizmos.values().stream()
                          .filter(g -> g.contains(x, y))
                          .findFirst().orElse(null);
    }

    private Ball getBallAt(double x, double y) {
        return this.balls.values().stream()
                         .filter(b -> b.contains(x, y))
                         .findFirst().orElse(null);
    }

    public void select(double x, double y) {
        this.selX = x;
        this.selY = y;
    }

    public void move(double dX, double dY) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            gizmo.setPosition((int) dX, (int) dY);
            return;
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setPosition(dX, dY);
        }
    }

    public void delete() {
        this.gizmos = this.gizmos.entrySet().stream()
                                 .filter(e -> !e.getValue().contains(this.selX, this.selY))
                                 .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        this.balls = this.balls.entrySet().stream()
                               .filter(e -> !e.getValue().contains(this.selX, this.selY))
                               .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    public void addGizmo(Gizmo.GizmoType type) { // FIXME Needs an identifier
    }

    public void rotateGizmo() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            gizmo.rotate();
        }
    }

    public void addBall(String identifier) {
        Ball ball = new Ball();
        ball.setPosition(this.selX, this.selY);
        // TODO: manage conflicting identifiers?
        this.balls.put(identifier, ball);
    }

    public void setBallVelocity(double vX, double vY) {
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setVelocity(new Vect(vX, vY));
        }
    }

    public double getGravity() {
        return this.gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getFrictionMu() {
        return this.mu;
    }

    public double getFrictionMu2() {
        return this.mu2;
    }

    public void setFriction(double mu, double mu2) {
        this.mu = mu;
        this.mu2 = mu2;
    }

    public void triggerOnKeyPress(int key) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.keyPressMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    public void triggerOnKeyRelease(int key) {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.keyReleaseMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    public void triggerOnOuterWalls() {
        Gizmo gizmo = this.getGizmoAt(this.selX, this.selY);
        if (gizmo != null) {
            this.wallTriggers.add(gizmo);
        }
    }

    public void triggerOnGizmo(double sX, double sY) {
        Gizmo source = this.getGizmoAt(sX, sY);
        Gizmo destination = this.getGizmoAt(this.selX, this.selY);
        if (source != null && destination != null) {
            this.gizmoMap.computeIfAbsent(source, s -> new HashSet<>()).add(destination);
        }
    }

    public void load(InputStream input) {
    }

    public OutputStream save() {
        return null;  // TODO
    }

    public Set<ReadGizmo> getGizmos() {
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
