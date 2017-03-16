package gizmos;

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
        assertEquals(mySpawner.getType(), GizmoType.SPAWNER);
    }

    @Test
    public void getWidth() {
        assertEquals(mySpawner.getWidth(), 1);
    }

    @Test
    public void getHeight() {
        assertEquals(mySpawner.getHeight(), 1);
    }

    @Test
    public void getLineSegments() {
        assertEquals(mySpawner.getLineSegments(), Collections.emptySet());
    }

    @Test
    public void getCircles() {
        assertEquals(mySpawner.getCircles(), Collections.emptySet());
    }

    @Test
    public void trigger() {
        Ball newBall = mySpawner.trigger();
        assertEquals(newBall.getPosition(), new Vect(mySpawner.getX() + 0.5, mySpawner.getY() + 0.5));
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(mySpawner.getReflectionCoefficient(), 1, 0);
    }

    @Test
    public void containsBall() {
        assertFalse(mySpawner.containsBall(new Ball()));
    }

    @Test
    public void ballHit() {
        Ball ball = new Ball();
        assertEquals(mySpawner.ballHit(ball), ball);
    }

    @Test
    public void getPivot() {
        assertEquals(mySpawner.getPivot(), new Vect(0, 0));
    }

    @Test
    public void getAngularVelocity() {
        assertEquals(mySpawner.getAngularVelocity(), 0d, DELTA);
    }
}