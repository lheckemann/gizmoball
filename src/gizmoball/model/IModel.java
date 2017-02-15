package gizmoball.model;

import java.io.InputStream;
import java.io.OutputStream;

public interface IModel extends ReadModel {
    // TODO: Decide whether the board has to be resetted before loading.
    void load(InputStream input) throws SyntaxError;

    /**
     * Returns a representation of the game in the standard format.
     * https://personal.cis.strath.ac.uk/murray.wood/Gizmoball/Gizmoball_spec.htm#file-format
     */
    void save(OutputStream output);
}
