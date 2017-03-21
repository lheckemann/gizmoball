package test.gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
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
        assertEquals(GizmoType.TRIANGLE, myTriangle.getType());
    }

    @Test
    public void getWidth() {
        assertEquals(1, myTriangle.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(1, myTriangle.getHeight());
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(
                Stream.of(new LineSegment(0, 0, 0, 1),
                        new LineSegment(0, 1, 1, 0),
                        new LineSegment(1, 0, 0, 0)).collect(Collectors.toSet())
        );

        assertEquals(lines, myTriangle.getLineSegments());
    }

    @Test
    public void getCircles() {
        final double circleOffset = 0.1;
        final double circleSize = 0.09;
        final Set<Circle> circles = Collections.unmodifiableSet(
                Stream.of(new Circle(circleOffset, 2*circleOffset, circleSize),
                        new Circle(circleOffset, 1-circleOffset, circleSize),
                        new Circle(1-2*circleOffset, circleOffset, circleSize)).collect(Collectors.toSet())
        );

        assertEquals(circles, myTriangle.getCircles());
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(1, myTriangle.getReflectionCoefficient(), 0);
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
    	assertEquals(ball, myTriangle.ballHit(ball));
    }
    
    @Test
    public void getPivot() {
    	assertEquals(new Vect(0, 0), myTriangle.getPivot());
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(0, myTriangle.getAngularVelocity(), DELTA);
    }
}