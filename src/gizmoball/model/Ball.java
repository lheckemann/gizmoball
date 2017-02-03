package gizmoball.model;

import physics.Vect;

public class Ball {
    private double x;
    private double y;
    private Vect velocity;

    public Ball() {
        this.x = 0d;
        this.y = 0d;
        this.velocity = new Vect(0d, 0d);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }
}
