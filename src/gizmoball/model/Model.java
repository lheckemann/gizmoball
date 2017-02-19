package gizmoball.model;

import physics.LineSegment;
import physics.Vect;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import gizmoball.model.gizmos.*;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;

import java.util.HashSet;
import java.util.Set;

public class Model implements BuildModel, RunModel {
    private final Set<LineSegment> walls = new HashSet<>();

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
        walls.add(new LineSegment(0, 0, 0, height));
        walls.add(new LineSegment(0, height, width, height));
        walls.add(new LineSegment(width, height, width, 0));
        walls.add(new LineSegment(width, 0, 0, 0));
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
                .filter(g -> g.getCells().contains(new Vect(x, y)))
                .findFirst().orElse(null);
    }


    /* ATTENTION: Multiple balls could be at the same position e.g. inside an
     * absorber */
    private Ball getBallAt(double x, double y) {
        return this.balls.stream()
                .filter(b -> b.contains(x, y))
                .findFirst().orElse(null);
    }

    private void checkPlacement(double x, double y) throws PositionOverlapException, PositionOutOfBoundsException {
        if (!(0 <= x && x < this.width && 0 <= y && y < this.height)) {
            throw new PositionOutOfBoundsException();
        }
        Vect cell = new Vect((int) x, (int) y);
        if (this.gizmos.stream().anyMatch(g -> g.getCells().contains(cell))) {
            throw new PositionOverlapException();
        }
        if (this.balls.stream().anyMatch(b -> b.getCells().contains(cell))) {
            throw new PositionOverlapException();
        }
    }

    private void checkPlacement(Gizmo gizmo) throws PositionOverlapException, PositionOutOfBoundsException {
        for (Vect cell : gizmo.getCells()) {
            this.checkPlacement(cell.x(), cell.y());
        }
    }

    private void checkPlacement(Ball ball) throws PositionOverlapException, PositionOutOfBoundsException {
        for (Vect cell : ball.getCells()) {
            this.checkPlacement(cell.x(), cell.y());
        }
    }

    @Override
    public void select(double x, double y) {
        this.selX = x;
        this.selY = y;
    }

    @Override
    public void move(double dX, double dY) throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = this.getGizmoAt((int) this.selX, (int) this.selY);
        if (gizmo != null) {
            int x = gizmo.getX();
            int y = gizmo.getY();
            gizmo.setX((int) dX);
            gizmo.setY((int) dY);
            try {
                this.checkPlacement(gizmo);
            } catch (PositionOverlapException|PositionOutOfBoundsException e) {
                gizmo.setX(x);
                gizmo.setY(y);
                throw e;
            }
            return;
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            double x = ball.getX();
            double y = ball.getY();
            ball.setX(dX);
            ball.setY(dY);
            try {
                this.checkPlacement(ball);
            } catch (PositionOverlapException|PositionOutOfBoundsException e) {
                ball.setX(x);
                ball.setY(y);
                throw e;
            }
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
            this.balls.remove(ball);
        }
    }

    @Override
    public void addAbsorber(int width, int height) throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Absorber(width, height);
        gizmo.setX((int)this.selX);
        gizmo.setY((int)this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
    }

    @Override
    public void addSquare() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Square();
        gizmo.setX((int)this.selX);
        gizmo.setY((int)this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
    }

    @Override
    public void addCircle() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Circle();
        gizmo.setX((int)this.selX);
        gizmo.setY((int)this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
    }

    @Override
    public void addTriangle() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Triangle();
        gizmo.setX((int)this.selX);
        gizmo.setY((int)this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
    }

    @Override
    public void addRightFlipper() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Flipper(false);
        gizmo.setX((int)this.selX);
        gizmo.setY((int)this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
    }

    @Override
    public void addLeftFlipper() throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = new Flipper(true);
        gizmo.setX((int)this.selX);
        gizmo.setY((int)this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
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
        ball.setX(this.selX);
        ball.setY(this.selY);
        ball.setVelocityX(velocityX);
        ball.setVelocityY(velocityY);
        this.checkPlacement(ball);
        this.balls.add(ball);
    }

    @Override
    public void setBallVelocity(double vX, double vY) {
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            ball.setVelocityX(vX);
            ball.setVelocityY(vY);
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
        this.gizmos.forEach(Gizmo::tick);
    }

    @Override
    public Set<ReadGizmo> getGizmos() {
        return Collections.unmodifiableSet(this.gizmos);
    }

    @Override
    public Set<ReadBall> getBalls() {
        return Collections.unmodifiableSet(this.balls);
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

    public void load(InputStream input) throws SyntaxError {
        double gravity = this.gravity;
        double mu = this.mu;
        double mu2 = this.mu2;
        Set<Gizmo> gizmos = new HashSet<>(this.gizmos);
        Set<Ball> balls = new HashSet<>(this.balls);
        Map<Integer,Set<Gizmo>> keyPressMap = new HashMap<>(this.keyPressMap);
        Map<Integer,Set<Gizmo>> keyReleaseMap = new HashMap<>(this.keyReleaseMap);
        Map<Gizmo,Set<Gizmo>> gizmoMap = new HashMap<>(this.gizmoMap);
        Set<Gizmo> wallTriggers = new HashSet<>(this.wallTriggers);
        this.reset();
        try {
            new Loader(this).load(input);
        } catch (SyntaxError e) {
            this.gravity = gravity;
            this.mu = mu;
            this.mu2 = mu2;
            this.gizmos = gizmos;
            this.balls = balls;
            this.keyPressMap = keyPressMap;
            this.keyReleaseMap = keyReleaseMap;
            this.gizmoMap = gizmoMap;
            this.wallTriggers = wallTriggers;
            throw e;
        }
    }

    //private void tickBall(Ball ball) {
    //    // TODO: does not handle multiple balls or moving flippers.
    //    CollisionFinder finder = new CollisionFinder(ball.getCircle(), ball.getVelocity());
    //    walls.forEach(finder.getLineConsumer());
    //    gizmos.values().stream().map(Gizmo::getLineSegments).flatMap(Collection::stream).forEach(finder.getLineConsumer());
    //    gizmos.values().stream().map(Gizmo::getCircles).flatMap(Collection::stream).forEach(finder.getCircleConsumer());

    //    if (finder.getTimeUntilCollision() < 1.0 / TICKS_PER_SECOND) {
    //        ball.position = finder.nextCollisionPosition();
    //        ball.velocity = finder.getNewVelocity();
    //    }
    //    else {
    //        ball.position = ball.position.plus(ball.velocity.times(1.0 / TICKS_PER_SECOND));
    //        ball.velocity = ball.velocity.plus(gravity);
    //    }
    //}

    public void save(OutputStream output) {
        new Saver(this).save(output);
    }
}
