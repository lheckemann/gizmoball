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
        assertEquals(myCircle.getType(), GizmoType.CIRCLE);
    }

    @Test
    public void getWidth() {
        assertEquals(myCircle.getWidth(), 1);
    }

    @Test
    public void getHeight() {
        assertEquals(myCircle.getHeight(), 1);
    }

    @Test
    public void getCircles() {
        physics.Circle physics = new physics.Circle(0.5, 0.5, 0.5);
        assertEquals(myCircle.getCircles(), Collections.singleton(physics));
    }

    @Test
    public void getLineSegments() {
        assertEquals(myCircle.getLineSegments(), Collections.emptySet());
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(myCircle.getReflectionCoefficient(), 1, 0);
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
    	assertEquals(myCircle.ballHit(ball), ball);
    }
    
    @Test
    public void getPivot() {
    	assertEquals(myCircle.getPivot(), new Vect(0, 0));
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(myCircle.getAngularVelocity(), 0d, DELTA);
    }
}