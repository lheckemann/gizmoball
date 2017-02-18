package gizmoball.model;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import physics.Vect;
import gizmoball.model.gizmos.*;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;

public class Model implements BuildModel, RunModel {

    private double width;
    private double height;
    private double selX;
    private double selY;
    private double gravity = 25;
    private double mu = 0.025;
    private double mu2 = 0.025;

    private Set<Gizmo> gizmos;
    private Set<Ball> balls;
    //A map from Key Id to Gizmo
    private Map<Integer, Set<Gizmo>> keyPressMap = new HashMap<>();
    //A map from Key Id to Gizmo
    private Map<Integer, Set<Gizmo>> keyReleaseMap = new HashMap<>();

    private Map<Gizmo, Set<Gizmo>> gizmoMap;
    private Set<Gizmo> wallTriggers;

    public Model(double width, double height) {
        this.width = width;
        this.height = height;
        this.reset();
    }

    @Override
    public void reset() {
        this.gizmos = new HashSet<>();
        this.keyPressMap = new HashMap<>();
        this.keyReleaseMap = new HashMap<>();
        this.gizmoMap = new HashMap<>();
        this.wallTriggers = new HashSet<>();
        this.balls = new HashSet<>();
    }

    private Gizmo getGizmoAt(int x, int y) {

        return this.gizmos.stream()
                .filter(g -> (g.getX() == x && g.getY() == y))
                .findFirst().orElse(null);
    }


    /*Multiple balls could be at the same position e.g. inside an absorber*/
    private Ball getBallAt(double x, double y) {
        return this.balls.stream()
                .filter(b -> b.contains(x, y))
                .findFirst().orElse(null);
    }

    @Override
    public void select(double x, double y) {
        this.selX = x;
        this.selY = y;
    }

    @Override
    public void move(double dX, double dY) throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        if (gizmo != null) {
            if (this.positionTaken(dX, dY)) {
                throw new PositionOverlapException();
            }
            else if(this.positionOutOfBounds(dX, dY)) {
                throw new PositionOutOfBoundsException();
            } else {
                gizmo.setX((int) dX);
                gizmo.setY((int) dY);
                return;
            }
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setX(dX);
            ball.setY(dY);
        }
    }

    @Override
    public void delete() {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        if (gizmo != null) {
            this.gizmos.remove(gizmo);
            for (Set<Gizmo> listeners : this.gizmoMap.values()) {
                listeners.remove(gizmo);
            }
            for (Set<Gizmo> listeners : this.keyPressMap.values()) {
                listeners.remove(gizmo);
            }
            for (Set<Gizmo> listeners : this.keyReleaseMap.values()) {
                listeners.remove(gizmo);
            }
            this.wallTriggers.remove(gizmo);
            return;
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            this.balls.remove(new Vect(this.selX, this.selY));
        }
    }

    private boolean positionTaken(double dX, double dY) {

        return false;
    }

    private boolean positionOutOfBounds(double dX, double dY) {
        return false;
    }

    @Override
    public void addAbsorber(int width, int height) throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Absorber(width, height);

        if (this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            gizmo.setX((int)this.selX);
            gizmo.setY((int)this.selY);
            this.gizmos.add(gizmo);
        }
    }

    @Override
    public void addSquare() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Square();

        if (this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            gizmo.setX((int)this.selX);
            gizmo.setY((int)this.selY);
            this.gizmos.add(gizmo);
        }
    }

    @Override
    public void addCircle() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Circle();

        if (this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            gizmo.setX((int)this.selX);
            gizmo.setY((int)this.selY);
            this.gizmos.add(gizmo);
        }
    }

    @Override
    public void addTriangle() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Triangle();

        if (this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            gizmo.setX((int)this.selX);
            gizmo.setY((int)this.selY);
            this.gizmos.add(gizmo);
        }
    }

    @Override
    public void addRightFlipper() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Flipper(false);

        if (this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            gizmo.setX((int)this.selX);
            gizmo.setY((int)this.selY);
            this.gizmos.add(gizmo);
        }
    }

    @Override
    public void addLeftFlipper() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Flipper(true);

        if (this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            gizmo.setX((int)this.selX);
            gizmo.setY((int)this.selY);
            this.gizmos.add(gizmo);
        }
    }

    @Override
    public void rotateGizmo() {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        if (gizmo != null) {
            gizmo.rotate();
        }
    }

    @Override
    public void addBall(double velocityX, double velocityY) throws PositionOverlapException, PositionOutOfBoundsException {

        Ball ball = new Ball();
        ball.setVelocity(new Vect(velocityX, velocityY));
        if(this.positionTaken(this.selX, this.selY)) {
            throw new PositionOverlapException();
        }
        else if (this.positionOutOfBounds(this.selX, this.selY)) {
            throw new PositionOutOfBoundsException();
        }
        else {
            ball.setX(this.selX);
            ball.setY(this.selY);
            this.balls.add(ball);
        }
    }

    @Override
    public void setBallVelocity(double vX, double vY) {
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setVelocity(new Vect(vX, vY));
        }
    }

    @Override
    public double getGravity() {
        return this.gravity;
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public double getFrictionMu() {
        return this.mu;
    }

    @Override
    public double getFrictionMu2() {
        return this.mu2;
    }

    @Override
    public void setFriction(double mu, double mu2) {
        this.mu = mu;
        this.mu2 = mu2;
    }

    @Override
    public void triggerOnKeyPress(int key) {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        if (gizmo != null) {
            this.keyPressMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    @Override
    public void triggerOnKeyRelease(int key) {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        if (gizmo != null) {
            this.keyReleaseMap.computeIfAbsent(key, k -> new HashSet<>()).add(gizmo);
        }
    }

    @Override
    public void triggerOnOuterWalls() {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        if (gizmo != null) {
            this.wallTriggers.add(gizmo);
        }
    }

    @Override
    public void triggerOnGizmo(ReadGizmo gizmo) {
        Gizmo destination = this.getGizmoAt((int)this.selX, (int)this.selY);
        Gizmo source = (Gizmo)gizmo;

        if (source != null && destination != null) {
            this.gizmoMap.computeIfAbsent(source, s -> new HashSet<>()).add(destination);
        }
    }
    @Override
    public void keyPressed(int keyCode) {
        this.keyPressMap.getOrDefault(keyCode, Collections.emptySet()).forEach(Gizmo::trigger);
    }

    @Override
    public void keyReleased(int keyCode) {
        this.keyReleaseMap.getOrDefault(keyCode, Collections.emptySet()).forEach(Gizmo::trigger);
    }

    @Override
    public void tick() {
        gizmos.forEach(Gizmo::tick);
    }

    @Override
    public Collection<ReadGizmo> getGizmos() {
        return Collections.unmodifiableCollection(gizmos);
    }

    @Override
    public Collection<ReadBall> getBalls() {
        return Collections.unmodifiableCollection(balls);
    }

    @Override
    public Map<Integer, Set<ReadGizmo>> getKeyPressToGizmoMap() {
        // A Map<T,Set<ReadGizmo> is not a supertype of Map<T,Set<Gizmo>>,
        // even if ReadGizmo is a supertype of Gizmo
        Map<Integer, Set<ReadGizmo>> copy = new HashMap<>();
        for (Integer i : this.keyPressMap.keySet()) {
            copy.put(i, new HashSet<>(this.keyPressMap.get(i)));
        }
        return copy;
    }

    @Override
    public Map<Integer, Set<ReadGizmo>> getKeyReleaseToGizmoMap() {
        // A Map<T,Set<ReadGizmo> is not a supertype of Map<T,Set<Gizmo>>,
        // even if ReadGizmo is a supertype of Gizmo
        Map<Integer, Set<ReadGizmo>> copy = new HashMap<>();
        for (Integer i : this.keyReleaseMap.keySet()) {
            copy.put(i, new HashSet<>(this.keyReleaseMap.get(i)));
        }
        return copy;
    }

    @Override
    public Map<ReadGizmo, Set<ReadGizmo>> getGizmoToGizmoMap() {
        // A Map<T,Set<ReadGizmo> is not a supertype of Map<T,Set<Gizmo>>,
        // even if ReadGizmo is a supertype of Gizmo
        Map<ReadGizmo, Set<ReadGizmo>> copy = new HashMap<>();
        for (Gizmo g : this.gizmoMap.keySet()) {
            copy.put(g, new HashSet<>(this.gizmoMap.get(g)));
        }
        return copy;
    }

    public void load(InputStream stream) throws SyntaxError {
        Loader loader = new Loader(this);
        loader.load(stream);
    }

    public void save(OutputStream stream) throws FileNotFoundException {
        Saver saver = new Saver(this);
        saver.save(stream);
    }
}
