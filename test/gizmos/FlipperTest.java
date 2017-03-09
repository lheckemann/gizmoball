package test.gizmos;

import gizmoball.model.gizmos.Flipper;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FlipperTest {
    private Gizmo myLeftFlipper;
    private Gizmo myRightFlipper;

    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        myLeftFlipper = new Flipper(true);
        myRightFlipper = new Flipper(false);
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
}