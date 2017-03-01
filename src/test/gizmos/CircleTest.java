package test.gizmos;

import gizmoball.model.Ball;
import gizmoball.model.gizmos.Circle;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class CircleTest {
    private Gizmo myCircle;

    @Before
    public void setUp() {
        myCircle = new Circle();
    }

    @Test
    public void getType() {
        assertEquals(myCircle.getType(), ReadGizmo.GizmoType.CIRCLE);
    }

    @Test
    public void getWidth() {
        assertEquals(myCircle.getHeight(), 1);
    }

    @Test
    public void getHeight() {
        assertEquals(myCircle.getHeight(), 1);
    }

    @Test
    public void getCircles() {
        physics.Circle physics = new physics.Circle(0.5, 0.5, 0.5);
        assertEquals(myCircle.getCircles(), Collections.singleton(physics));
    }

    @Test
    public void getLineSegments() {
        assertEquals(myCircle.getLineSegments(), Collections.emptySet());
    }

    @Test
    public void getReflectionCoefficient() {
        assertEquals(myCircle.getReflectionCoefficient(), 1, 0);
    }
}