package gizmoball.model.gizmos;

import gizmoball.model.Ball;
import physics.*;
import physics.Circle;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class Spawner extends BaseGizmo {
    @Override
    public GizmoType getType() {
        return GizmoType.SPAWNER;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public Set<LineSegment> getLineSegments() {
        return Collections.emptySet();
    }

    @Override
    public Set<Circle> getCircles() {
        return Collections.emptySet();
    }

    private static final Random random = new Random();
    @Override
    public Ball trigger() {
        Ball ret = new Ball();
        ret.setPosition(new Vect(getX() + 0.5, getY() + 0.5));
        ret.setVelocityX(random.nextDouble() * 1e-5 - 5e-6);
        ret.setVelocityY(random.nextDouble() * 1e-5 - 5e-6);
        return ret;
    }
}
