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
        this.rotation = this.rotation.nextCW();
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

    public abstract Set<LineSegment> getLineSegments();

    public abstract Set<physics.Circle> getCircles();

    public boolean containsBall(Ball ball) {
        return false;
    }

    public Ball ballHit(Ball ball) {
        return ball;
    }

    @Override
    public AffineTransform getTransform() {
        int m00, m01, m02, m10, m11, m12;
        m00 = m01 = m02 = m10 = m11 = m12 = 0;
        switch (this.getRotation()) {
            case NORTH:
                m00 =  1; m01 =  0; m02 = this.getX();
                m10 =  0; m11 =  1; m12 = this.getY();
                break;
            case EAST:
                m00 =  0; m01 = -1; m02 = this.getHeight() + this.getX();
                m10 =  1; m11 =  0; m12 = this.getY();
                break;
            case SOUTH:
                m00 = -1; m01 =  0; m02 = this.getWidth() + this.getX();
                m10 =  0; m11 =  -1; m12 = this.getHeight() + this.getY();
                break;
            case WEST:
                m00 =  0; m01 =  1; m02 = this.getX();
                m10 = -1; m11 =  0; m12 = this.getWidth() + this.getY();
                break;
        }
        return new AffineTransform(m00, m10, m01, m11, m02, m12);
    }

    public Vect getPivot() {
        return new Vect(0, 0);
    }

    public Double getAngularVelocity() {
        return 0d;
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
