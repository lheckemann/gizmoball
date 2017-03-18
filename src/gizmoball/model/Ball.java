package gizmoball.model;

import java.util.*;

import physics.*;

public class Ball implements ReadBall {
    public final static double RADIUS = 0.25;

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

    public Ball(Ball src) {
        x = src.getX();
        y = src.getY();
        velocityX = src.getVelocityX();
        velocityY = src.getVelocityY();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getRadius() {
        return RADIUS;
    }

    public Vect getPosition() {
        return new Vect(x, y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPosition(Vect pos) {
        x = pos.x();
        y = pos.y();
    }

    @Override
    public double getVelocityX() {
        return this.velocityX;
    }

    @Override
    public double getVelocityY() {
        return this.velocityY;
    }

    public Vect getVelocity() {
        return new Vect(velocityX, velocityY);
    }

    public void setVelocityX(double vX) {
        this.velocityX = vX;
    }

    public void setVelocityY(double vY) {
        this.velocityY = vY;
    }

    public void setVelocity(Vect v) {
        velocityX = v.x();
        velocityY = v.y();
    }

    public boolean contains(double x, double y) {
        return RADIUS > Math.sqrt(Math.pow(x - this.getX(), 2.0) + Math.pow(y - this.getY(), 2.0));
    }

    public Circle getCircle() {
        return new Circle(x, y, RADIUS);
    }

    /**
     * Returns the cells occupied by the ball
     */
    public Set<Vect> getCells() {
        Set<Vect> cells = new HashSet<>();
        for (int i : Arrays.asList(-1, 1)) {
            for (int j : Arrays.asList(-1, 1)) {
                int x = (int)Math.floor((this.getX() + i * this.getRadius()));
                int y = (int)Math.floor((this.getY() + j * this.getRadius()));
                cells.add(new Vect(x, y));
            }
        }
        return cells;
    }
}
