package gizmoball.model.gizmos;

public interface ReadGizmo {
    public static enum Rotation {
        N(0), E(1), S(2), W(3);

        private int turns;
        Rotation(int turns) {
            this.turns = turns;
        }

        public int getTurns() {
            return this.turns;
        }
    }

    public static enum GizmoType {
        ABSORBER, SQUARE, CIRCLE, TRIANGLE, RIGHT_FLIPPER, LEFT_FLIPPER;
    }

    public GizmoType getType();
    public Rotation getRotation();
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
}
