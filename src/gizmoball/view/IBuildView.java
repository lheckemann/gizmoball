package gizmoball.view;

public interface IBuildView {
    public void promptVelocity();
    public double getPromptedVelocityX();
    public double getPromptedVelocityY();
    public String getFrictionMuText();
    public String getFrictionMu2Text();
    public String getGravityText();
    // Defined in GameView
    public void updateBoard();
}
