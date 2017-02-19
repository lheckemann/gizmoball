package gizmoball.model.gizmos;

public enum Rotation {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private int turns;

    Rotation(int turns) {
        this.turns = turns;
    }

    public int getTurns() {
        return this.turns;
    }

    public double getRadiansFromNorth() {
        return Math.PI / 2 * turns;
    }
}
