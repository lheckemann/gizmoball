package gizmoball.view;

import java.io.File;

import gizmoball.model.BuildModel;
import gizmoball.controller.save.Saver;

public interface IGizmoBallView {
    File getFileByChooserLoad();
    File getFileByChooserSave();
    void displayErrorMessage(String message, String title);
    void switchToBuildView();
    void switchToRunView();
    void updateBoard();
    Saver promptSaverType(BuildModel model);
    void reset();
}
