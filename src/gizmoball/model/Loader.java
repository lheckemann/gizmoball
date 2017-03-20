package gizmoball.model;

import java.io.InputStream;

public interface Loader {
    void load(BuildModel model, InputStream input) throws SyntaxError;
}
