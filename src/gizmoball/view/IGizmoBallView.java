package gizmoball.view;

import java.io.File;

public interface IGizmoBallView {
    public File getFileByChooser();
    public void displayErrorMessage(String message, String title);
    public void switchToBuildView();
    public void switchToRunView();
    public void updateBoard();
}
