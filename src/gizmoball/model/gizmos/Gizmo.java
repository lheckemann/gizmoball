package gizmoball.model.gizmos;

import gizmoball.model.*;
import gizmoball.model.Geometry;
import physics.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Gizmo implements ReadGizmo {
    private Rotation rotation;
    private int x;
    private int y;

    public Gizmo() {
        this.rotation = Rotation.NORTH;
        this.x = 0;
        this.y = 0;
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public void rotate() {
        switch (this.rotation) {
            case NORTH:
                this.rotation = Rotation.EAST;
                break;
            case EAST:
                this.rotation = Rotation.SOUTH;
                break;
            case SOUTH:
                this.rotation = Rotation.WEST;
                break;
            default:
                this.rotation = Rotation.NORTH;
        }
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public abstract GizmoType getType();

    public abstract int getWidth();

    public abstract int getHeight();

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void tick() {
    }

    public double getReflectionCoefficient() {
        return 1.0;
    }

    public void trigger() {
    }

    @Override
    public int getPivotAngle() throws GizmoTypeException {
        throw new GizmoTypeException();
    }

    private Set<LineSegment> lineCache = null;
    public Set<LineSegment> getLineSegments() {
        AffineTransform transform = computeTransform();
        if (lineCache == null) {
            lineCache = getBasicLineSegments().stream()
                    .map(line -> gizmoball.model.Geometry.transformThrough(transform, line))
                    .collect(Collectors.toSet());
        }
        return Collections.unmodifiableSet(lineCache);
    }
    protected abstract Set<LineSegment> getBasicLineSegments();

    private Set<physics.Circle> circleCache = null;
    public Set<physics.Circle> getCircles() {
        AffineTransform transform = computeTransform();
        if (circleCache == null) {
            circleCache = getBasicCircles().stream()
                    .map(circle -> Geometry.transformThrough(transform, circle))
                    .collect(Collectors.toSet());
        }
        return Collections.unmodifiableSet(circleCache);
    }
    protected abstract Set<physics.Circle> getBasicCircles();

    protected AffineTransform computeTransform() {
        AffineTransform result = new AffineTransform();
        result.translate(getX(), getY());
        result.translate(getWidth()/2, getHeight()/2);
        result.quadrantRotate(getRotation().getTurns());
        result.translate(getWidth()/-2, getHeight()/-2);
        return result;
    }

    public AffineTransform getTransform() {
        return new AffineTransform(computeTransform());
    }

    public boolean containsCell(int x, int y) {
        return this.getX() <= x && x < this.getX() + this.getWidth() &&
               this.getY() <= y && y < this.getY() + this.getWidth();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
         }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Gizmo other = (Gizmo) obj;
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }
}
