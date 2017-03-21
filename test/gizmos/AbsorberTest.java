package test.gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Absorber;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.InvalidAbsorberWidthHeight;
import gizmoball.model.gizmos.NonRotatableException;
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
import static org.junit.Assert.assertEquals;

public class AbsorberTest {
    private Gizmo myAbsorber;
    private int width;
    private int height;
    private Ball myBall;
    
    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        width = 5;
        height = 10;
        try {
            myAbsorber = new Absorber(5, 10);
        } catch (InvalidAbsorberWidthHeight e) {
            fail();
        }
        myBall = new Ball();
    }

    @Test
    public void getType() {
        assertEquals(GizmoType.ABSORBER, myAbsorber.getType());
    }

    @Test
    public void getWidth() {
        assertEquals(width, myAbsorber.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(height, myAbsorber.getHeight());
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(
                Stream.of(
                        new LineSegment(0, 0, 0, height),
                        new LineSegment(0, height, width, height),
                        new LineSegment(width, height, width, 0),
                        new LineSegment(width, 0, 0, 0)
                ).collect(Collectors.toSet())
        );
        assertEquals(lines, myAbsorber.getLineSegments());
    }

    @Test
    public void getCircles() {
        final double circleOffset = 0.1;
        final double circleSize = 0.09;
        final Set<Circle> circles = Collections.unmodifiableSet(
                Stream.of(
                        new Circle(circleOffset, circleOffset, circleSize),
                        new Circle(circleOffset, myAbsorber.getHeight()-circleOffset, circleSize),
                        new Circle(myAbsorber.getWidth()-circleOffset, myAbsorber.getHeight()-circleOffset, circleSize),
                        new Circle(myAbsorber.getWidth()-circleOffset, circleOffset, circleSize)).collect(Collectors.toSet()));

        assertEquals(circles, myAbsorber.getCircles());
    }

    @Test
    public void triggerWithNoBalls() {
        assertNull(myAbsorber.trigger());
    }

    @Test
    public void triggerWithOneBall() {
        myAbsorber.ballHit(myBall);
        assertEquals(myBall, myAbsorber.trigger());
    }

    @Test
    public void triggerWithMultipleBalls() {
        Ball newBall = new Ball();
        myAbsorber.ballHit(myBall);
        myAbsorber.ballHit(newBall);

        Ball res = myAbsorber.trigger();
        assertTrue( res.equals(myBall) || res.equals(newBall));
    }

    @Test
    public void containsBallTrue() {
        assertTrue(myAbsorber.containsBall(myBall));
    }

    @Test
    public void containsBallFalse() {
        Ball newBall = new Ball();
        newBall.setPosition(new Vect(19, 19));
        assertFalse(myAbsorber.containsBall(newBall));
    }

    @Test
    public void ballHitAbsorberNowContainsBall() {
        myAbsorber.ballHit(myBall);
        assertTrue(myAbsorber.containsBall(myBall));
    }

    @Test
    public void ballHitReturnNull() {
        assertNull(myAbsorber.ballHit(myBall));
    }

    @Test
    public void rotateAbsorber() {
        Gizmo myAbsorberCopy;
        try {
            myAbsorberCopy = new Absorber(width, height);
            myAbsorberCopy.rotate();

            // TODO : check with equals if they are the same
            fail();
        } catch (InvalidAbsorberWidthHeight e) {
            e.printStackTrace();
        } catch (NonRotatableException e) {
            assertTrue(true);
        }
   
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(1, myAbsorber.getReflectionCoefficient(), 0);
    }
    
    @Test
    public void getPivot() {
    	assertEquals(new Vect(0, 0), myAbsorber.getPivot());
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(0, myAbsorber.getAngularVelocity(), DELTA);
    }
}