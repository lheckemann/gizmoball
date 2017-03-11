package gizmoball.model;


public interface ReadBall {
    /**
     * Returns the velocity accross the X axis.
     */
    public double getVelocityX();

    /**
     * Returns the velocity accross the Y axis.
     */
    public double getVelocityY();

    /**
     * Returns the position on the X axis.
     */
    public double getX();

    /**
     * Returns the position on the Y axis.
     */
    public double getY();

    /**
     * Returns the radius.
     */
    double getRadius();
}
