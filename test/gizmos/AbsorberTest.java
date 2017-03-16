package gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Absorber;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.InvalidAbsorberWidthHeight;
import gizmoball.model.gizmos.NonRotatableException;
import gizmoball.model.gizmos.ReadGizmo;
import org.junit.Before;
import org.junit.Test;
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        myBall = new Ball();
    }

    @Test
    public void getType() {
        assertEquals(myAbsorber.getType(), GizmoType.ABSORBER);
    }

    @Test
    public void getWidth() {
        assertEquals(myAbsorber.getWidth(), width);
    }

    @Test
    public void getHeight() {
        assertEquals(myAbsorber.getHeight(), height);
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
        assertEquals(myAbsorber.getLineSegments(), lines);
    }

    @Test
    public void getCircles() {
        assertEquals(myAbsorber.getCircles(), Collections.emptySet());
    }

    @Test
    public void triggerWithNoBalls() {
        assertNull(myAbsorber.trigger());
    }

    @Test
    public void triggerWithOneBall() {
        myAbsorber.ballHit(myBall);
        assertEquals(myAbsorber.trigger(), myBall);
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NonRotatableException e) {
            assertTrue(true);
        }
   
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(myAbsorber.getReflectionCoefficient(), 1, 0);
    }
    
    @Test
    public void getPivot() {
    	assertEquals(myAbsorber.getPivot(), new Vect(0, 0));
    }
    
    @Test
    public void getAngularVelocity() {
    	assertEquals(myAbsorber.getAngularVelocity(), 0d, DELTA);
    }
}