package test.gizmos;

import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Square;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class SquareTest {
    private Gizmo mySquare;

    @Before
    public void setUp() throws Exception {
        mySquare = new Square();
    }

    @Test
    public void getType() {
        assertEquals(mySquare.getType(), ReadGizmo.GizmoType.SQUARE);
    }

    @Test
    public void getWidth() {
        assertEquals(mySquare.getWidth(), 1);
    }

    @Test
    public void getHeight() {
        assertEquals(mySquare.getHeight(), 1);
    }

    @Test
    public void getLineSegments() {
        final Set<LineSegment> lines = Collections.unmodifiableSet(
                Stream.of(new LineSegment(0, 0, 0, 1),
                        new LineSegment(0, 1, 1, 1),
                        new LineSegment(1, 1, 1, 0),
                        new LineSegment(1, 0, 0, 0)).collect(Collectors.toSet())
        );
        assertEquals(mySquare.getLineSegments(), lines);
    }

    @Test
    public void getCircles() {
        final double circleSize = 0.2;
        final Set<Circle> circles = Collections.unmodifiableSet(
                Stream.of(new Circle(circleSize, circleSize, circleSize),
                        new Circle(circleSize, 1-circleSize, circleSize),
                        new Circle(1-circleSize, 1-circleSize, circleSize),
                        new Circle(1-circleSize, circleSize, circleSize)).collect(Collectors.toSet())
        );
        assertEquals(mySquare.getCircles(), circles);
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(mySquare.getReflectionCoefficient(), 1, 0);
    }
}