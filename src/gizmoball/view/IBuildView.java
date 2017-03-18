package gizmoball.view;

public interface IBuildView {
    void promptVelocity();
    double getPromptedVelocityX();
    double getPromptedVelocityY();
    void setDisplayLabel(String displayText);
    void setCursor(CustomCursorType c);
    // Defined in GameView
    void updateBoard();
    void displayErrorMessage(String message, String title);

}
