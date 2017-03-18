package gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Circle;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.ReadGizmo;
import physics.Vect;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class CircleTest {
    private Gizmo myCircle;
    
    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        myCircle = new Circle();
    }

    @Test
    public void getType() {
        assertEquals(GizmoType.CIRCLE, myCircle.getType());
    }

    @Test
    public void getWidth() {
        assertEquals(1, myCircle.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(1, myCircle.getHeight());
    }

    @Test
    public void getCircles() {
        physics.Circle physics = new physics.Circle(0.5, 0.5, 0.5);
        assertEquals(Collections.singleton(physics), myCircle.getCircles());
    }

    @Test
    public void getLineSegments() {
        assertEquals(Collections.emptySet(), myCircle.getLineSegments());
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(1, myCircle.getReflectionCoefficient(), 0);
    }
    
    @Test
    public void trigger() {
    	assertNull(myCircle.trigger());
    }
    
    @Test
    public void containsBall() {
    	assertFalse(myCircle.containsBall(new Ball()));
    }
    
    @Test
    public void ballHit() {
    	Ball ball = new Ball();
    	assertEquals(ball, myCircle.ballHit(ball));
    }
    
    @Test
    public void getPivot() {
    	assertEquals(new Vect(0, 0), myCircle.getPivot());
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(0, myCircle.getAngularVelocity(), DELTA);
    }
}