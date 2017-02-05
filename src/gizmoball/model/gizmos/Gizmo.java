package gizmoball.model.gizmos;

public abstract class Gizmo implements ReadGizmo {
    private Rotation rotation;
    private int x;
    private int y;

    public Gizmo() {
        this.rotation = Rotation.N;
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public void rotate() {
        switch (this.rotation) {
            case N:
                this.rotation = Rotation.E;
                break;
            case E:
                this.rotation = Rotation.S;
                break;
            case S:
                this.rotation = Rotation.W;
                break;
            default:
                this.rotation = Rotation.N;
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

    public boolean contains(double x, double y) {
        return (this.x <= x && x < this.x + this.getWidth() &&
                this.y <= y && y < this.y + this.getHeight());
    }

    public abstract GizmoType getType();
    public abstract int getWidth();
    public abstract int getHeight();

    public void tick() {};
    public void trigger() {};
}
