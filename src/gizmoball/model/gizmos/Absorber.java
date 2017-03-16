package gizmoball.model.gizmos;

import physics.*;
import physics.Circle;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gizmoball.model.Ball;
import gizmoball.model.ReadBall;

public class Absorber extends BaseGizmo {
    private final int width;
    private final int height;
    private Set<Ball> balls;

    public Absorber(int width, int height) throws InvalidAbsorberWidthHeight {
        if (Math.abs(width) < 1 || Math.abs(height) < 1) {
            System.out.println("Threw exception");
            throw new InvalidAbsorberWidthHeight("Absorber (width, height) was smaller than (1, 1)");
        }
        
        this.width = width;
        this.height = height;
        this.balls = new HashSet<Ball>();
    }

    @Override
    public GizmoType getType() {
        return GizmoType.ABSORBER;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
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

    @Override
    public Ball trigger() {
        // Makes the absorber fire a single ball
        if (balls.size() > 0) {
            Ball ball = this.balls.iterator().next();
            this.balls.remove(ball);
            ball.setVelocity(new Vect(0, -50));
            return ball;
        }
        return null;
    }

    @Override
    public boolean containsBall(ReadBall ball) {
        return this.getX() <= ball.getX() &&
               ball.getX() < this.getX() + this.getWidth() &&
               this.getY() <= ball.getY() &&
               ball.getY() < this.getY() + this.getHeight();
    }

    @Override
    public Ball ballHit(Ball ball) {
        ball.setVelocity(new Vect(0, 0));
        ball.setPosition(new Vect(this.getX() + width - 0.25 - Ball.RADIUS,
                                  this.getY() + height - 0.25 - Ball.RADIUS));
        this.balls.add(ball);
        return null;
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
