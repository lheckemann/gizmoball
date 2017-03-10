package gizmoball.model.gizmos;

import physics.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Flipper extends Gizmo {
    protected static final double ANGULAR_VELOCITY = 6d * Math.PI;
    protected boolean isLeftFlipper;
    protected double pivotAngle;

    protected Flipper(boolean isLeft) {
        pivotAngle = 0d;
        isLeftFlipper = isLeft;
    }

    @Override
    public double getReflectionCoefficient() {
        // TODO take angular velocity into account? Is that possible?
        return 0.95f;
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public Vect getPivot() {
        return new Vect(0.25, 0.25);
    }
}
