package gizmoball.view;

public interface IBuildView {
    public void promptVelocity();
    public double getPromptedVelocityX();
    public double getPromptedVelocityY();
    public void setDisplayLabel(String displayText);
    public void setCursor(CustomCursorType c);
    // Defined in GameView
    public void updateBoard();
}
