package gizmoball.controller.load;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gizmoball.model.Model;
import gizmoball.model.SyntaxError;
import gizmoball.view.IGizmoBallView;

public class LoadListener implements ActionListener {
    private final Model model;
    private final IGizmoBallView view;

    public LoadListener(Model model, IGizmoBallView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File loadedFile = view.getFileByChooserLoad();
            if(loadedFile != null) {
                model.load(new FileInputStream(loadedFile));
            }
            view.updateBoard();
        } catch (FileNotFoundException fnfe) {
            view.displayErrorMessage(fnfe.getMessage(), "File not found");
        } catch (SyntaxError err) {
            view.displayErrorMessage(err.getMessage(), "Syntax error in file");
        }
    }
}