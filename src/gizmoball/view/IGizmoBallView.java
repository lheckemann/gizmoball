package gizmoball.view;

import java.io.File;

// FIXME: Get out of here
import gizmoball.controller.save.SaverType;
import gizmoball.model.Model;

public interface IGizmoBallView {
    public File getFileByChooserLoad();
    public File getFileByChooserSave();
    public void displayErrorMessage(String message, String title);
    public void switchToBuildView();
    public void switchToRunView();
    public void updateBoard();
    public void promptSaveType(Model model);
    public void reset();
}
