package gizmoball.model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.util.*;

public class Model {
    public static final int TICKS_PER_SECOND = 20;
    private static final int HEIGHT = 20,
                            WIDTH = 20;
    private Map<Integer, Set<Gizmo>> keyMap;
    private Set<Gizmo> gizmos;
    private static final List<LineSegment> walls = new ArrayList<>();
    static {
        walls.add(new LineSegment(0, 0, 0, HEIGHT));
        walls.add(new LineSegment(0, HEIGHT, WIDTH, HEIGHT));
        walls.add(new LineSegment(WIDTH, HEIGHT, WIDTH, 0));
        walls.add(new LineSegment(WIDTH, 0, 0, 0));
    }
    private Vect gravity;

    public void keyPressed(int key) {
        keyMap.get(key).forEach(Gizmo::keyPressed);
    }
    public void keyReleased(int key) {
        keyMap.get(key).forEach(Gizmo::keyReleased);
    }
    public Model() {
        keyMap = new HashMap<>();
        balls = new HashSet<>();
        gizmos = new HashSet<>();
        gravity = new Vect(0, 0);
    }


    private class Ball {
        // In Ls
        Vect position;
        // In Ls/second
        Vect velocity;

        Circle circle;

        public Ball(Vect pos, Vect vel) {
            position = pos;
            velocity = vel;
        }

        public Ball() {
            this(new Vect(0, 0), new Vect(0, 0));
        }
    }

    private Set<Ball> balls;

    public void tick() {
        balls.forEach(this::tickBall);
    }

    private void tickBall(Ball ball) {
        // TODO: does not handle multiple balls or moving flippers.
        CollisionFinder finder = new CollisionFinder(ball.circle, ball.velocity);
        walls.forEach(finder.getLineConsumer());
        gizmos.stream().map(Gizmo::getLineSegments).flatMap(Collection::stream).forEach(finder.getLineConsumer());
        gizmos.stream().map(Gizmo::getCircles).flatMap(Collection::stream).forEach(finder.getCircleConsumer());

        if (finder.getTimeUntilCollision() < 1.0 / TICKS_PER_SECOND) {
            ball.position = finder.nextCollisionPosition();
            ball.velocity = finder.getNewVelocity();
        }
        else {
            ball.position = ball.position.plus(ball.velocity.times(1.0 / TICKS_PER_SECOND));
            ball.velocity = ball.velocity.plus(gravity);
        }
    }
}
