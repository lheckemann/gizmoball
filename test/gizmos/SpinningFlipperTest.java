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
        assertEquals(GizmoType.LEFT_SPINNING_FLIPPER, myLeftFlipper.getType());
    }

    @Test
    public void getTypeRight() {
        assertEquals(GizmoType.RIGHT_SPINNING_FLIPPER, myRightFlipper.getType());
    }

    @Test
    public void getAngularVelocityLeftFlipperNotRunning() {
        myLeftFlipper.trigger();
        assertEquals(0, myLeftFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityLeftFlipper() {
        assertEquals(-6d * Math.PI, myLeftFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getAngularVelocityRightFlipper() {
        assertEquals(6d * Math.PI, myRightFlipper.getAngularVelocity(), DELTA);
    }

    @Test
    public void getPivot() {
        assertEquals(new Vect(1.25, 1.25), myLeftFlipper.getPivot());
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(Stream.of(
                new LineSegment(0, 0.25, 0, 0.75),
                new LineSegment(0.5, 0.25, 0.5, 0.75)
        ).collect(Collectors.toSet()));

        assertEquals(lines, myLeftFlipper.getLineSegments());
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