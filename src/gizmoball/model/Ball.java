package gizmoball.model;

import physics.Vect;

import java.util.*;

public class Ball implements ReadBall {
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

    public void setVelocityX(double vX) {
        this.velocityX = vX;
    }

    public void setVelocityY(double vY) {
        this.velocityY = vY;
    }

    public Set<Vect> getBoundingBoxCells() {
        return Collections.singleton(new Vect(this.getX(), this.getY()));
    }
}
