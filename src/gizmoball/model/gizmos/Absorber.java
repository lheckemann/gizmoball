package gizmoball.model.gizmos;

import physics.*;
import physics.Circle;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gizmoball.model.Ball;

public class Absorber extends Gizmo {
    private final int width;
    private final int height;
    private Set<Ball> balls;

    public Absorber(int width, int height) {
        this.width = width;
        this.height = height;
        this.balls = new HashSet<Ball>();
    }

    public GizmoType getType() {
        return GizmoType.ABSORBER;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    //@Override
    public Set<LineSegment> getLineSegments() {
        return Collections.unmodifiableSet(
            Stream.of(
                    new LineSegment(0, 0, 0, height),
                    new LineSegment(0, height, width, height),
                    new LineSegment(width, height, width, 0),
                    new LineSegment(width, 0, 0, 0)
            ).collect(Collectors.toSet())
        );
    }

    public void addBall(Ball b) {
        b.setVelocity(new Vect(0, 0));
        b.setPosition(new Vect(this.getX() + width - 0.25, this.getY() + height - 0.25));
        b.setInAbsorber(true);
        this.balls.add(b);
    }

    @Override
    /*
     * Makes the absorber fire a single ball
     */
    public Ball trigger() {
        if (balls.size() > 0) {
            Ball ballToFire = this.balls.iterator().next();
            balls.remove(ballToFire);
            ballToFire.setPosition(new Vect(this.getX() + width - 0.25, this.getY() - 0.25));
            ballToFire.setVelocity(new Vect(0, -33));
            ballToFire.setHasBeenFired(true);
            return ballToFire;
        }
        return null;
    }

    public Ball ballHit(Ball ball) {
        ball.setVelocity(new Vect(0, 0));
        ball.setPosition(new Vect(this.getX() + width - 0.25, this.getY() + height - 0.25));
        this.balls.add(ball);
        return null;
    }

    public boolean containsBall() {
        return balls.size() > 0;
    }

    @Override
    public Set<Circle> getCircles() {
        // TODO
        return Collections.emptySet();
    }

    @Override
    public void rotate() {
    }
}
