package model;

import physics.Circle;
import physics.LineSegment;

import java.util.Set;

public abstract class Gizmo {
    public void keyPressed() {
    }
    public void keyReleased() {
    }

    public void trigger() {
        keyPressed();
    }

    public abstract Set<LineSegment> getLineSegments();
    public abstract Set<Circle> getCircles();
}
