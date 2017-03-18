package gizmoball.view;

import java.io.File;

// FIXME: Get out of here
import gizmoball.controller.save.SaverType;
import gizmoball.model.Model;

public interface IGizmoBallView {
    File getFileByChooserLoad();
    File getFileByChooserSave();
    void displayErrorMessage(String message, String title);
    void switchToBuildView();
    void switchToRunView();
    void updateBoard();
    void promptSaveType(Model model);
    void reset();
}
