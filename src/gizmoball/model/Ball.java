package gizmoball.model;

import physics.Vect;

public class Ball implements ReadBall {
    private final static double RADIUS = 0.5;
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
    
    public double getVelocityX() {
        return this.velocityX;
    }

    public double getVelocityY() {
        return this.velocityY;
    }

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public boolean contains(double x, double y) {
        return RADIUS > Math.sqrt(Math.pow(x - this.x, 2.0) + Math.pow(y - this.y, 2.0));
    }
}
