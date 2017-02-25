package gizmoball.model;

import java.util.*;
import java.util.stream.*;
import java.awt.geom.AffineTransform;

import physics.*;
import static physics.Geometry.*;

import gizmoball.model.gizmos.Gizmo;
import static gizmoball.model.Geometry.*;

public class CollisionFinder {
    private Set<Ball> balls;
    private Set<LineSegment> walls;
    private double wallReflection;
    private Set<Gizmo> gizmos;

    public static class Collision {
        public final Ball ball;
        public final double time;
        public final LineSegment line;
        public final Circle circle;
        public final Ball againstBall;
        public final Gizmo againstGizmo;

        public Collision(Ball ball, double time,
                LineSegment line, Circle circle,
                Ball againstBall, Gizmo againstGizmo) {
            this.ball = ball;
            this.time = time;
            this.line = line;
            this.circle = circle;
            this.againstBall = againstBall;
            this.againstGizmo = againstGizmo;
        }
    }

    public void setGizmos(Set<Gizmo> gizmos) {
        this.gizmos = gizmos;
    }

    public void setWalls(Set<LineSegment> walls, double reflection) {
        this.walls = walls;
        this.wallReflection = reflection;
    }

    public void setBalls(Set<Ball> balls) {
        this.balls = balls;
    }

    public Vect getCollisionPosition(Collision c) {
        return c.ball.getCircle().getCenter().plus(c.ball.getVelocity().times(c.time));
    }

    public Vect getCollisionVelocity(Collision c) {
        Vect v = new Vect(c.ball.getVelocityX(), c.ball.getVelocityY());
        double reflection = this.wallReflection;
        if (c.againstGizmo != null) {
            reflection = c.againstGizmo.getReflectionCoefficient();
        } else if (c.againstBall != null) {
            reflection = 1.0;  // TODO: delegate to ball
        }
        if (c.line != null) {
            v = reflectWall(c.line, v, reflection);
        } else if (c.circle != null) {
            v = reflectCircle(c.circle.getCenter(), c.ball.getCircle().getCenter(), v, reflection);
        }
        return v;
    }

    public Set<Collision> getCollisions(double time) {
        Set<Collision> collisions = new HashSet<>();
        for (Ball ball : this.balls) {
            Stream.of(
                    this.getWallCollisions(ball),
                    this.getGizmoCollisions(ball),
                    this.getBallCollisions(ball)
                    )
                .flatMap(Set::stream)
                .filter(c -> c.time < time)
                .sorted(Comparator.comparing(c -> c.time))
                .findFirst()
                .ifPresent(c -> collisions.add(c));
        }
        return collisions;
    }

    public Set<Collision> getWallCollisions(Ball ball) {
        Set<Collision> collisions = new HashSet<>();
        for (LineSegment l : this.walls) {
            double t = timeUntilWallCollision(l, ball.getCircle(), ball.getVelocity());
            collisions.add(new Collision(ball, t, l, null, null, null));
        }
        return collisions;
    }

    public Set<Collision> getGizmoCollisions(Ball ball) {
        Set<Collision> collisions = new HashSet<>();
        for (Gizmo g : this.gizmos) {
            if (g.containsBall(ball)) { continue; }
            AffineTransform transform = g.getTransform();
            for (LineSegment l : g.getLineSegments()) {
                l = transformThrough(transform, l);
                double t = timeUntilWallCollision(l, ball.getCircle(), ball.getVelocity());
                collisions.add(new Collision(ball, t, l, null, null, g));
            }
            for (Circle c : g.getCircles()) {
                c = transformThrough(transform, c);
                double t = timeUntilCircleCollision(c, ball.getCircle(), ball.getVelocity());
                collisions.add(new Collision(ball, t, null, c, null, g));
            }
        }
        return collisions;
    }

    public Set<Collision> getBallCollisions(Ball ball) {
        Set<Collision> collisions = new HashSet<>();
        for (Ball b : this.balls) {
            if (b.equals(ball)) { continue; }
            double t = timeUntilBallBallCollision(ball.getCircle(), ball.getVelocity(),
                                                  b.getCircle(), b.getVelocity());
            collisions.add(new Collision(ball, t, null, b.getCircle(), b, null));
        }
        return collisions;
    }
}
