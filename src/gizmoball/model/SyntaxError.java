package gizmoball.model;

public class SyntaxError extends Exception {
    public static final long serialVersionUID = 1L;

    public SyntaxError(String msg) {
        super(msg);
    }
}
