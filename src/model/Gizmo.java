package model;

public abstract class Gizmo implements ReadGizmo {
    private int x, y;
    private Rotation rotation;

    protected Gizmo() {
        rotation = Rotation.NORTH;
    }

    public float getReflectionCoefficient() {
        return 1;
    }

    public void trigger() {
    }

    public void tick() {
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void rotateCW() {
        switch (rotation) {
            case NORTH:
                rotation = Rotation.EAST;
                break;
            case EAST:
                rotation = Rotation.SOUTH;
                break;
            case SOUTH:
                rotation = Rotation.WEST;
                break;
            case WEST:
                rotation = Rotation.NORTH;
                break;
        }
    }

    public void setRotation(Rotation rot) {
        rotation = rot;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getPivotAngle() throws GizmoTypeException {
        throw new GizmoTypeException();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
