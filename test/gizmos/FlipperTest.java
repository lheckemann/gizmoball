package test.gizmos;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.*;

public class FlipperTest {
    private Gizmo myLeftFlipper;
    private Gizmo myRightFlipper;

    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        myLeftFlipper = new StandardFlipper(true);
        myRightFlipper = new StandardFlipper(false);
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(myLeftFlipper.getReflectionCoefficient(), 0.95f, DELTA);
    }

    @Test
    public void trigger() {
        assertNull(myLeftFlipper.trigger());
    }

    @Test
    public void tick() {
        // TODO
    }

    @Test
    public void getWidth() {
        assertEquals(myLeftFlipper.getWidth(), 2);
    }

    @Test
    public void getHeight() {
        assertEquals(myLeftFlipper.getHeight(), 2);
    }

    @Test
    public void getTypeLeft() {
        assertEquals(myLeftFlipper.getType(), ReadGizmo.GizmoType.LEFT_FLIPPER);
    }

    @Test
    public void getTypeRight() {
        assertEquals(myRightFlipper.getType(), ReadGizmo.GizmoType.RIGHT_FLIPPER);
    }

    @Test
    public void getTransform() {
        // TODO
    }
    
    @Test
    public void getPivot() {
    	assertEquals(new Vect(0.25, 0.25), myLeftFlipper.getPivot());
    }
    
    @Test
    public void getAngularVelocityLeftFlipperNoTick() {
    	assertEquals(myLeftFlipper.getAngularVelocity(), 0.0, DELTA);
    }
    
    @Test
    public void getAngularVelocityLeftFlipperOneTick() {
    	myLeftFlipper.tick();
    	assertEquals(myLeftFlipper.getAngularVelocity(), 0.0, DELTA);
    }
    
    @Test
    public void getAngularVelocityLeftFlipperOneTickAndActive() {
    	myLeftFlipper.trigger();
    	myLeftFlipper.tick();
    	assertEquals(myLeftFlipper.getAngularVelocity(), -6d * Math.PI, DELTA);
    }
    
    @Test
    public void getAngularVelocityLeftFlipperOneTickAndThenNotActive() {
    	myLeftFlipper.trigger();
    	myLeftFlipper.tick();
    	myLeftFlipper.trigger();
    	assertNotEquals(myLeftFlipper.getAngularVelocity(), 6d * Math.PI, DELTA);
    }
    
    //
    @Test
    public void getAngularVelocityRightFlipperNoTick() {
    	assertEquals(myRightFlipper.getAngularVelocity(), 0.0, DELTA);
    }
    
    @Test
    public void getAngularVelocityRightFlipperOneTick() {
    	myRightFlipper.tick();
    	assertEquals(myRightFlipper.getAngularVelocity(), 0.0, DELTA);
    }
    
    @Test
    public void getAngularVelocityRightFlipperOneTickAndActive() {
    	myRightFlipper.trigger();
    	myRightFlipper.tick();
    	assertEquals(myRightFlipper.getAngularVelocity(), 6d * Math.PI, DELTA);
    }
    
    @Test
    public void getAngularVelocityRightFlipperOneTickAndThenNotActive() {
    	myRightFlipper.trigger();
    	myRightFlipper.tick();
    	myRightFlipper.trigger();
    	assertNotEquals(myRightFlipper.getAngularVelocity(), -6d * Math.PI, DELTA);
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
                new LineSegment(0, 0.25, 0, 1.75),
                new LineSegment(0.5, 0.25, 0.5, 1.75)
        ).collect(Collectors.toSet()));

        assertEquals(myLeftFlipper.getLineSegments(), lines);
    }

    @Test
    public void getCircles() {
        final Set<Circle> circles = Collections.unmodifiableSet(Stream.of(
                new Circle(0.25, 0.25, 0.25),
                new Circle(0.25, 1.75, 0.25),
                new Circle(0, 0.25, 0.05),
                new Circle(0, 1.75, 0.05),
                new Circle(0.5, 0.25, 0.05),
                new Circle(0.5, 1.75, 0.05)
        ).collect(Collectors.toSet()));

        assertEquals(myLeftFlipper.getCircles(), circles);
    }
    
    @Test
    public void containsBall() {
    	assertFalse(myLeftFlipper.containsBall(new Ball()));
    }
    
    @Test
    public void ballHit() {
    	Ball ball = new Ball();
    	assertEquals(myLeftFlipper.ballHit(ball), ball);
    }
}
