package gizmoball.model.gizmos;

public abstract class Gizmo implements ReadGizmo {
    private Rotation rotation;
    private int x;
    private int y;

    public Gizmo() {
        this.rotation = Rotation.NORTH;
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public void rotate() {
        switch (this.rotation) {
            case NORTH:
                this.rotation = Rotation.EAST;
                break;
            case EAST:
                this.rotation = Rotation.SOUTH;
                break;
            case SOUTH:
                this.rotation = Rotation.WEST;
                break;
            default:
                this.rotation = Rotation.NORTH;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public boolean contains(double x, double y) {
        return (this.x <= x && x < this.x + this.getWidth() &&
                this.y <= y && y < this.y + this.getHeight());
    }

    public abstract GizmoType getType();
    public abstract int getWidth();
    public abstract int getHeight();

    public void tick() {};

    public double getReflectionCoefficient() {
        return 1.0;
    };

    public void trigger() {};

    @Override
    public int getPivotAngle() throws GizmoTypeException {
        throw new GizmoTypeException();
    }
}
