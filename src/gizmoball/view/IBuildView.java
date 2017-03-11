package gizmoball.view;

public interface IBuildView {
    public void promptVelocity();
    public double getPromptedVelocityX();
    public double getPromptedVelocityY();
    // Defined in GameView
    public void updateBoard();
}
