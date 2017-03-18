package gizmoball.model;


public interface ReadBall {
    /**
     * Returns the velocity accross the X axis.
     */
    double getVelocityX();

    /**
     * Returns the velocity accross the Y axis.
     */
    double getVelocityY();

    /**
     * Returns the position on the X axis.
     */
    double getX();

    /**
     * Returns the position on the Y axis.
     */
    double getY();

    /**
     * Returns the radius.
     */
    double getRadius();
}
