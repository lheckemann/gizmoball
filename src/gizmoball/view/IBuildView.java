package gizmoball.view;

public interface IBuildView {
    //Returns false if the user decides to cancel the entering velocity action
    //Otherwise returns true
    boolean promptVelocity();
    double getPromptedVelocityX();
    double getPromptedVelocityY();
    void setDisplayLabel(String displayText);
    void setCursor(CustomCursorType c);
    // Defined in GameView
    void updateBoard();
    void displayErrorMessage(String message, String title);

}
