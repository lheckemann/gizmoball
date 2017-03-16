package gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Triangle;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TriangleTest {
    private Gizmo myTriangle;
    
    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        myTriangle = new Triangle();
    }

    @Test
    public void getType() {
        assertEquals(myTriangle.getType(), GizmoType.TRIANGLE);
    }

    @Test
    public void getWidth() {
        assertEquals(myTriangle.getWidth(), 1);
    }

    @Test
    public void getHeight() {
        assertEquals(myTriangle.getHeight(), 1);
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(
                Stream.of(new LineSegment(0, 0, 0, 1),
                        new LineSegment(0, 1, 1, 0),
                        new LineSegment(1, 0, 0, 0)).collect(Collectors.toSet())
        );

        assertEquals(myTriangle.getLineSegments(), lines);
    }

    @Test
    public void getCircles() {
        final double circleSize = 0.2;
        final Set<Circle> circles = Collections.unmodifiableSet(
                Stream.of(new Circle(circleSize, 2*circleSize, circleSize),
                        new Circle(circleSize, 1-circleSize, circleSize),
                        new Circle(1-2*circleSize, circleSize, circleSize)).collect(Collectors.toSet())
        );

        assertEquals(myTriangle.getCircles(), circles);
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(myTriangle.getReflectionCoefficient(), 1, 0);
    }
    
    @Test
    public void trigger() {
    	assertNull(myTriangle.trigger());
    }
    
    @Test
    public void containsBall() {
    	assertFalse(myTriangle.containsBall(new Ball()));
    }
    
    @Test
    public void ballHit() {
    	Ball ball = new Ball();
    	assertEquals(myTriangle.ballHit(ball), ball);
    }
    
    @Test
    public void getPivot() {
    	assertEquals(myTriangle.getPivot(), new Vect(0, 0));
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(myTriangle.getAngularVelocity(), 0d, DELTA);
    }
}