package gizmoball.model;

import physics.Vect;

import java.util.*;

public class Ball implements ReadBall {
    private final static double RADIUS = 0.25;

    private double x;
    private double y;
    private double velocityX;
    private double velocityY;

    public Ball() {
        this.x = 0d;
        this.y = 0d;
        this.velocityX = 0d;
        this.velocityY = 0d;
    }

    public Ball clone() {
        Ball b = new Ball();
        b.setX(this.x);
        b.setY(this.y);
        b.setVelocityX(this.velocityX);
        b.setVelocityY(this.velocityY);
        return b;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getVelocityX() {
        return this.velocityX;
    }

    public double getVelocityY() {
        return this.velocityY;
    }
    
    public double getDiameter() {
        return RADIUS * 2;
    }

    public void setVelocityX(double vX) {
        this.velocityX = vX;
    }

    public void setVelocityY(double vY) {
        this.velocityY = vY;
    }

    public boolean contains(double x, double y) {
        return RADIUS > Math.sqrt(Math.pow(x - this.getX(), 2.0) + Math.pow(y - this.getY(), 2.0));
    }

    public Set<Vect> getCells() {
        return Collections.singleton(new Vect((int) this.getX(), (int) this.getY()));
    }
}
