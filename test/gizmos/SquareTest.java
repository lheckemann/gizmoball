package test.gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.Square;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


public class SquareTest {
    private Gizmo mySquare;

    private final double DELTA = 1e-15;
    
    @Before
    public void setUp() throws Exception {
        mySquare = new Square();
    }

    @Test
    public void getType() {
        assertEquals(GizmoType.SQUARE, mySquare.getType());
    }

    @Test
    public void getWidth() {
        assertEquals(1, mySquare.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(1, mySquare.getHeight());
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(
                Stream.of(new LineSegment(0, 0, 0, 1),
                        new LineSegment(0, 1, 1, 1),
                        new LineSegment(1, 1, 1, 0),
                        new LineSegment(1, 0, 0, 0)).collect(Collectors.toSet())
        );
        assertEquals(lines, mySquare.getLineSegments());
    }

    @Test
    public void getCircles() {
        final double circleSize = 0.1;
        final Set<Circle> circles = Collections.unmodifiableSet(
                Stream.of(new Circle(circleSize, circleSize, circleSize),
                        new Circle(circleSize, 1-circleSize, circleSize),
                        new Circle(1-circleSize, 1-circleSize, circleSize),
                        new Circle(1-circleSize, circleSize, circleSize)).collect(Collectors.toSet())
        );
        assertEquals(circles, mySquare.getCircles());
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(1, mySquare.getReflectionCoefficient(), 0);
    }
    
    @Test
    public void trigger() {
    	assertNull(mySquare.trigger());
    }
    
    @Test
    public void containsBall() {
    	assertFalse(mySquare.containsBall(new Ball()));
    }
    
    @Test
    public void ballHit() {
    	Ball ball = new Ball();
    	assertEquals(ball, mySquare.ballHit(ball));
    }
    
    @Test
    public void getPivot() {
    	assertEquals(new Vect(0, 0), mySquare.getPivot());
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(0, mySquare.getAngularVelocity(), DELTA);
    }
}