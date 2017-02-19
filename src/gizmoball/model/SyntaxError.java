package gizmoball.model;

public class SyntaxError extends Exception {
    public static final long serialVersionUID = 1L;
    private final Integer lineNumber;
    private final String line;
    private String message;

    public SyntaxError(Integer lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public SyntaxError(Integer lineNumber, String line, String message) {
        this.lineNumber = lineNumber;
        this.line = line;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format("Line %d. (\"%s\"): %s", this.lineNumber, this.line, this.message);
    }
}
