import java.io.InputStream;
import java.io.OutputStream;

public interface BuildModel {
    /**
     * Seleects the ball or gizmo at the provided location as an operand in
     * future operations. Gizmos are selected by their bounding boxes.
     */
    public void select(double x, double y);

    /**
     * Moves the selection to the given location.
     * If a gizmo is selected, the destination will be obtained from the integer
     * parts of the coordinates.
     * If there is no selection or there is no ball or gizmo at the selected
     * location, this will effectively be a noop.
     * TODO: moving outside of the arena (noop or a deletion?)
     * TODO: moving to an already occupied location
     */
    public void move(double dX, double dY);

    /**
     * Deletes the current selection.
     * If there is no selection or there is no ball or gizmo at the selected
     * location, this will effectively be a noop.
     */
    public void delete();

    /**
     * Creates a new gizmo at the selected location.
     * If there is no selection or the selected location is already occupied by
     * some other item, this will effectively be a noop.
     */
    public void addGizmo(GizmoType type);

    /**
     * Rotates the gizmo at the selected location clockwise.
     * If there is no selection or the selected location is not a gizmo, this
     * will effectively be a noop.
     */
    public void rotateGizmo();

    /**
     * Creates a new ball at the selected location.
     * If there is no selection or the selected location is already occupied by
     * some other item, this will effectively be a noop.
     */
    public void addBall();

    /**
     * Sets the velocity of the ball at the selected location.
     * If not set, the default initial velocity for a ball is 50L/s.
     * If there is no selection or the selected location is not a ball, this
     * will effectively be a noop.
     */
    public void setBallVelocity(double vX, double vY);

    /**
     * Gets the global gravity constant.
     * If not set, the default global gravity is 25L/s.
     */
    public void getGravity();

    /**
     * Sets the global gravity constant.
     * If not set, the default global gravity is 25L/s.
     */
    public void setGravity(double gravity);

    /**
     * Gets the global frictional constant μ (mu).
     * v_new = v_old * (1 - μ*Δt - μ₂*Δt*|v_old|)
     * If not set, the default value for μ is 0.025/s.
     */
    public void getFrictionMu();

    /**
     * Gets the global frictional constant μ₂ (mu2).
     * v_new = v_old * (1 - μ*Δt - μ₂*Δt*|v_old|)
     * If not set, the default value for μ₂ is 0.025/L.
     */
    public void getFrictionMu2();

    /**
     * Sets the global frictional constants μ (mu) and μ₂ (mu2).
     * If not set, the default value for μ is 0.025/s and the default value for
     * μ₂ is 0.025/s.
     */
    public void setFriction(double mu, double mu2);

    /**
     * Connects key presses of the given key to the triggering of the currently
     * selected gizmo.
     * If there is no selection or the selected location is not a gizmo, this
     * will effectively be a noop.
     */
    public void connectKeyPress(int key);

    /**
     * Connects key releases of the given key to the triggering of the currently
     * selected gizmo.
     * If there is no selection or the selected location is not a gizmo, this
     * will effectively be a noop.
     */
    public void connectKeyRelease(int key);

    // TODO: Discussion on telegram
    public void connectItems(double dX, double dY);

    /**
     * Resets all the state related to a particular game.
     * This includes all the gizmos, balls, connections and the gravity and
     * friction.
     */
    public void reset();

    // TODO: Decide whether the board has to be resetted before loading.
    public void load(InputStream input);

    /**
     * Returns a representation of the game in the standard format.
     * https://personal.cis.strath.ac.uk/murray.wood/Gizmoball/Gizmoball_spec.htm#file-format
     */
    public OutputStream save();
}
