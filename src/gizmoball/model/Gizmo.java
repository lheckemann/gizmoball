public abstract class Gizmo {
    public static enum Rotation {
        N, E, S, W;
    }
    public static enum GizmoType {
        ABSORBER, SQUARE, CIRCLE, TRIANGLE, RIGHT_FLIPPER, LEFT_FLIPPER;
    }

    private GizmoType type;
    private Rotation rotation;
    private int x;
    private int y;

    public Gizmo(GizmoType type) {
        this.type = type;
        this.rotation = Rotation.N;
    }

    public GizmoType getType() {
        return this.type;
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

    public abstract int getWidth();
    public abstract int getHeight();
}
