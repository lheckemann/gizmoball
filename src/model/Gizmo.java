package src.model;

public abstract class Gizmo {
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

    public void untrigger() {
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
}
