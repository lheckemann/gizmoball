package gizmoball.model.gizmos;

import java.awt.geom.AffineTransform;
import java.util.*;

import physics.*;

import gizmoball.model.*;
import gizmoball.model.Geometry;


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

    public Ball trigger() {
        return null;
    }

    @Override
    public int getPivotAngle() throws GizmoTypeException {
        throw new GizmoTypeException();
    }

    public Set<LineSegment> getLineSegments() {
        return Collections.emptySet();
    }

    public Set<physics.Circle> getCircles() {
        return Collections.emptySet();
    }

    public Ball ballHit(Ball ball) {
        return ball;
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform result = new AffineTransform();
        result.translate(getX(), getY());
        result.translate(getWidth()/2, getHeight()/2);
        result.quadrantRotate(getRotation().getTurns());
        result.translate(getWidth()/-2, getHeight()/-2);
        return result;
    }

    public Set<Vect> getCells() {
        Set<Vect> cells = new HashSet<>();
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                cells.add(new Vect(this.getX() + x, this.getY() + y));
            }
        }
        return cells;
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
