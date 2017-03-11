package gizmoball.view;

public interface IBuildView {
    public void promptVelocity();
    public double getPromptedVelocityX();
    public double getPromptedVelocityY();
    public void setDisplayLabel(String displayText);
    // Defined in GameView
    public void updateBoard();
}
