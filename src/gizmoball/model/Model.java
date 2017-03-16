package gizmoball.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import physics.LineSegment;
import physics.Vect;
import gizmoball.controller.Loader;
import gizmoball.model.gizmos.*;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import static gizmoball.model.CollisionFinder.Collision;


public class Model implements BuildModel, RunModel {
    private final Set<LineSegment> walls = new HashSet<>();

    private int width;
    private int height;
    private double selX;
    private double selY;
    private Vect gravity = new Vect(0, 25);
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
    private final double WALL_REFLECTION = 1.0;

    public Model(int width, int height) {
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
    public boolean notEmpty(double x, double y) {
        return (this.getGizmoAt((int) x, (int) y) != null || this.getBallAt(x, y) != null);
    }

    @Override
    public void move(double dX, double dY) throws PositionOverlapException, PositionOutOfBoundsException {
        Gizmo gizmo = this.getGizmoAt((int) this.selX, (int) this.selY);
        if (gizmo != null) {
            this.gizmos.remove(gizmo);
            int x = gizmo.getX();
            int y = gizmo.getY();
            gizmo.setX((int) dX);
            gizmo.setY((int) dY);
            try {
                this.checkPlacement(gizmo);
                this.gizmos.add(gizmo);
            } catch (PositionOverlapException|PositionOutOfBoundsException e) {
                gizmo.setX(x);
                gizmo.setY(y);
                this.gizmos.add(gizmo);
                throw e;
            } finally {
                this.gizmos.add(gizmo);
            }
            return;
        }
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            this.balls.remove(ball);
            double x = ball.getX();
            double y = ball.getY();
            ball.setX(dX);
            ball.setY(dY);

            try {
                this.checkPlacement(ball);
                this.balls.add(ball);
            } catch (PositionOverlapException|PositionOutOfBoundsException e) {
                ball.setX(x);
                ball.setY(y);
                this.balls.add(ball);
                throw e;
            } finally {
                this.balls.add(ball);
            }
        }
    }

    @Override
    public void delete() {
        Gizmo gizmo = this.getGizmoAt((int)this.selX, (int)this.selY);
        Ball ball = this.getBallAt(this.selX, this.selY);
        if (ball != null) {
            this.balls.remove(ball);
            return;
        }
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
        }
    }

    @Override
    public void addGizmo(Gizmo gizmo) throws PositionOverlapException, PositionOutOfBoundsException {
        gizmo.setX((int) this.selX);
        gizmo.setY((int) this.selY);
        this.checkPlacement(gizmo);
        this.gizmos.add(gizmo);
    }

    @Override
    public void rotateGizmo() throws NonRotatableException {
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
        return this.gravity.y();
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = new Vect(0, gravity);
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
        if (destination == null) {
            return;
        }
        for (Gizmo source : this.gizmos) {
            if (source.equals(gizmo)) {
                this.gizmoMap.computeIfAbsent(source, s -> new HashSet<>()).add(destination);
                return;
            }
        }
    }

    @Override
    public void triggerOnGizmoAt(double x, double y) {
        Gizmo gizmo = this.getGizmoAt((int)x, (int)y);
        if (gizmo != null) {
            this.triggerOnGizmo(gizmo);
        }
    }

    private void triggerGizmos(Set<Gizmo> gizmos) {
        if (gizmos == null) {
            return;
        }
        for (Gizmo g : gizmos) {
            Ball ball = g.trigger();
            if (ball != null) {
                this.balls.add(ball);
            }
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        this.triggerGizmos(this.keyPressMap.get(keyCode));
    }

    @Override
    public void keyReleased(int keyCode) {
        this.triggerGizmos(this.keyReleaseMap.get(keyCode));
    }

    public void gizmoHit(Gizmo gizmo, Ball ball) {
        this.balls.remove(ball);
        Ball rBall = gizmo.ballHit(ball);
        if (rBall != null) {
            this.balls.add(rBall);
        }
        this.triggerGizmos(this.gizmoMap.get(gizmo));
    }

    public void wallHit() {
        this.triggerGizmos(this.wallTriggers);
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
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
        Vect gravity = new Vect(this.gravity.x(), this.gravity.y());
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

    private void applyGlobalForces(Ball ball) {
        double friction = (1 - mu * SECONDS_PER_TICK - mu2 * ball.getVelocity().length() * SECONDS_PER_TICK);
        ball.setVelocity(ball.getVelocity().times(friction));
        Vect gravity = this.gravity.times(SECONDS_PER_TICK);
        ball.setVelocity(ball.getVelocity().plus(gravity));
    }

    @Override
    public void tick() {
        this.gizmos.forEach(Gizmo::tick);

        CollisionFinder finder = new CollisionFinder();
        finder.setWalls(this.walls, this.WALL_REFLECTION);
        finder.setGizmos(this.gizmos);
        finder.setBalls(this.balls);

        Set<Collision> handled = new HashSet<>();
        Set<Ball> unhandled = new HashSet<>(this.balls);
        for (;;) {
            Collision c = finder.getCollision(SECONDS_PER_TICK, handled);
            if (c == null) {
                break;
            }

            c.ball.setPosition(finder.getCollisionPosition(c));
            c.ball.setVelocity(finder.getCollisionVelocity(c));

            if (c.againstGizmo != null) {
                this.gizmoHit(c.againstGizmo, c.ball);
            } else if (c.againstBall != null) {
                // TODO ball hit
            } else {
                this.wallHit();
            }

            handled.add(c);
            unhandled.remove(c.ball);
        }
        for (Ball b : unhandled) {
            b.setPosition(b.getPosition().plus(b.getVelocity().times(SECONDS_PER_TICK)));
        }
        balls.forEach(this::applyGlobalForces);
    }
}
