package model;

public enum Rotation {
    NORTH, EAST, SOUTH, WEST;

    public double radiansFromNorth() {
        switch(this) {
            case NORTH: return 0;
            case EAST: return Math.PI/2;
            case SOUTH: return Math.PI;
            case WEST: return Math.PI/2 * 3;
        }
        assert false : "All cases should be covered by now";
        return Double.NaN;
    }
}
