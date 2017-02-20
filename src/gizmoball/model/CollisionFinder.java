package gizmoball.model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import java.util.function.Consumer;

public class CollisionFinder {
    private final Circle ball;
    private final Vect ballVelocity;
    private double nextCollisionTime;
    private Vect nextCollisionPos;
    private Vect newBallVelocity;

    public void consumeLine(LineSegment line, double reflection) {
        double time = Geometry.timeUntilWallCollision(line, ball, ballVelocity);
        if (time < nextCollisionTime) {
            nextCollisionTime = time;
            nextCollisionPos = ball.getCenter().plus(ballVelocity.times(time));
            newBallVelocity = Geometry.reflectWall(line, ballVelocity);
            newBallVelocity = Geometry.applyReflectionCoeff(ballVelocity, newBallVelocity, reflection);
        }
    }

    public void consumeCircle(Circle circle, double reflection) {
        double time = Geometry.timeUntilCircleCollision(circle, ball, ballVelocity);
        if (time < nextCollisionTime) {
            nextCollisionTime = time;
            nextCollisionPos = ball.getCenter().plus(ballVelocity.times(time));
            // TODO: add reflection coefficient
            newBallVelocity = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), ballVelocity);
        }
    }

    public double getTimeUntilCollision() {
        return nextCollisionTime;
    }

    public Vect nextCollisionPosition() {
        return nextCollisionPos;
    }

    public Vect getNewVelocity() {
        return newBallVelocity;
    }

    public CollisionFinder(Circle ball, Vect ballVel) {
        this.ball = ball;
        ballVelocity = ballVel;
        nextCollisionTime = Double.POSITIVE_INFINITY;
        nextCollisionPos = null;
        newBallVelocity = ballVel;
    }
}