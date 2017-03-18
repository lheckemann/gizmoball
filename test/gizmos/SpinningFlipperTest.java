package gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.model.gizmos.SpinningFlipper;
import gizmoball.model.gizmos.StandardFlipper;
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

public class SpinningFlipperTest {
    private Gizmo myLeftFlipper;
    private Gizmo myRightFlipper;

    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        myLeftFlipper = new SpinningFlipper(true);
        myRightFlipper = new SpinningFlipper(false);
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
        assertEquals(myLeftFlipper.getType(), GizmoType.LEFT_SPINNING_FLIPPER);
    }

    @Test
    public void getTypeRight() {
        assertEquals(myRightFlipper.getType(), GizmoType.RIGHT_SPINNING_FLIPPER);
    }

    @Test
    public void getTransform() {
        // TODO
    }

    @Test
    public void getAngularVelocityLeftFlipperNotRunning() {
        myLeftFlipper.trigger();
        assertEquals(myLeftFlipper.getAngularVelocity(), 0.0, DELTA);
    }

    @Test
    public void getAngularVelocityLeftFlipper() {
        assertEquals(myLeftFlipper.getAngularVelocity(), -6d * Math.PI, DELTA);
    }

    @Test
    public void getAngularVelocityRightFlipper() {
        assertEquals(myRightFlipper.getAngularVelocity(), 6d * Math.PI, DELTA);
    }

    @Test
    public void getPivot() {
        assertEquals(new Vect(0.25, 0.25), myLeftFlipper.getPivot());
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
                new LineSegment(0, 0.25, 0, 0.75),
                new LineSegment(0.5, 0.25, 0.5, 0.75)
        ).collect(Collectors.toSet()));

        assertEquals(myLeftFlipper.getLineSegments(), lines);
    }

    @Test
    public void getCircles() {
        final Set<physics.Circle> circles = Collections.unmodifiableSet(Stream.of(
                new physics.Circle(0.25, 0.25, 0.25),
                new physics.Circle(0.25, 0.75, 0.25),
                new physics.Circle(0, 0.25, 0.05),
                new physics.Circle(0, 0.75, 0.05),
                new physics.Circle(0.5, 0.25, 0.05),
                new physics.Circle(0.5, 0.75, 0.05)
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