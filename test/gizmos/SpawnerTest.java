package test.gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.Spawner;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import java.util.Collections;

import static org.junit.Assert.*;

public class SpawnerTest {
    private Gizmo mySpawner;

    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        mySpawner = new Spawner();
    }

    @Test
    public void getType() {
        assertEquals(GizmoType.SPAWNER, mySpawner.getType());
    }

    @Test
    public void getWidth() {
        assertEquals(1, mySpawner.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(1, mySpawner.getHeight());
    }

    @Test
    public void getLineSegments() {
        assertEquals(Collections.emptySet(), mySpawner.getLineSegments());
    }

    @Test
    public void getCircles() {
        assertEquals(Collections.emptySet(), mySpawner.getCircles());
    }

    @Test
    public void trigger() {
        Ball newBall = mySpawner.trigger();
        assertEquals(new Vect(mySpawner.getX() + 0.5, mySpawner.getY() + 0.5), newBall.getPosition());
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(1, mySpawner.getReflectionCoefficient(), 0);
    }

    @Test
    public void containsBall() {
        assertFalse(mySpawner.containsBall(new Ball()));
    }

    @Test
    public void ballHit() {
        Ball ball = new Ball();
        assertEquals(ball, mySpawner.ballHit(ball));
    }

    @Test
    public void getPivot() {
        assertEquals(new Vect(0, 0), mySpawner.getPivot());
    }

    @Test
    public void getAngularVelocity() {
        assertEquals(0, mySpawner.getAngularVelocity(), DELTA);
    }
}