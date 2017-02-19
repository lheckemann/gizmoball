package gizmoball.model;

import java.util.*;

import physics.Vect;

public class Ball implements ReadBall {
    private double x;
    private double y;

    //VelocityX and Y are needed since it is not possible to get them from Vect
    //without them begin changed (since Vect.x() and Vect.y() changes the x and y values,
    //making them different to what was passed in at construction.)
    private double velocityX;
    private double velocityY;
    private Vect velocity;

    public Ball() {
        this.velocityX = 0d;
        this.velocityY = 0d;
        this.velocity = new Vect(this.velocityX, this.velocityY);
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

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public Set<Vect> getBoundingBoxCells() {
        return Collections.singleton(new Vect(this.getX(), this.getY()));
    }
}
