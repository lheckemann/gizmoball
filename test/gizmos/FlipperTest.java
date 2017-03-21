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
import static gizmoball.model.RunModel.SECONDS_PER_TICK;

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
        assertEquals(0.95f, myLeftFlipper.getReflectionCoefficient(), DELTA);
    }

    @Test
    public void trigger() {
        assertNull(myLeftFlipper.trigger());
    }

    @Test
    public void getWidth() {
        assertEquals(2, myLeftFlipper.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(2, myLeftFlipper.getHeight());
    }

    @Test
    public void getTypeLeft() {
        assertEquals(GizmoType.LEFT_FLIPPER, myLeftFlipper.getType());
    }

    @Test
    public void getTypeRight() {
        assertEquals(GizmoType.RIGHT_FLIPPER, myRightFlipper.getType());
    }

    @Test
    public void getPivot() {
    	assertEquals(new Vect(0.25, 0.25), myLeftFlipper.getPivot());
    }

    @Test
    public void getAngularVelocityLeftFlipperNoTick() {
    	assertEquals(0, myLeftFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityLeftFlipperOneTick() {
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	assertEquals(0, myLeftFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityLeftFlipperOneTickAndActive() {
    	myLeftFlipper.trigger();
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	assertEquals(-6d * Math.PI, myLeftFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityLeftFlipperOneTickAndThenNotActive() {
    	myLeftFlipper.trigger();
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	myLeftFlipper.trigger();
    	assertNotEquals(6d * Math.PI, myLeftFlipper.getAngularVelocity(), DELTA);
    }
    
    @Test
    public void getAngularVelocityLeftFlipperFewTicksAndThenGoBack() {
    	myLeftFlipper.trigger();
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	myLeftFlipper.trigger();
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	myLeftFlipper.tick(SECONDS_PER_TICK);
    	assertEquals(0, myLeftFlipper.getAngularVelocity(), DELTA);
    }


    @Test
    public void getAngularVelocityRightFlipperNoTick() {
    	assertEquals(0, myRightFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityRightFlipperOneTick() {
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	assertEquals(0, myRightFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityRightFlipperOneTickAndActive() {
    	myRightFlipper.trigger();
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	assertEquals(6d * Math.PI, myRightFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityRightFlipperOneTickAndThenNotActive() {
    	myRightFlipper.trigger();
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	myRightFlipper.trigger();
    	assertNotEquals(-6d * Math.PI, myRightFlipper.getAngularVelocity(), DELTA);
    }
    
    @Test
    public void getAngularVelocityRightFlipperFewTicksAndThenGoBack() {
    	myRightFlipper.trigger();
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	myRightFlipper.trigger();
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	myRightFlipper.tick(SECONDS_PER_TICK);
    	assertEquals(0, myRightFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
                new LineSegment(0, 0.25, 0, 1.75),
                new LineSegment(0.5, 0.25, 0.5, 1.75)
        ).collect(Collectors.toSet()));

        assertEquals(lines, myLeftFlipper.getLineSegments());
    }

    @Test
    public void getCircles() {
        final Set<physics.Circle> circles = Collections.unmodifiableSet(Stream.of(
                new physics.Circle(0.25, 0.25, 0.25),
                new physics.Circle(0.25, 1.75, 0.25)
        ).collect(Collectors.toSet()));

        assertEquals(circles, myLeftFlipper.getCircles());
    }

    @Test
    public void containsBall() {
    	assertFalse(myLeftFlipper.containsBall(new Ball()));
    }

    @Test
    public void ballHit() {
    	Ball ball = new Ball();
    	assertEquals(ball, myLeftFlipper.ballHit(ball));
    }
}
