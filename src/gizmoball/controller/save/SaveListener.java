package gizmoball.controller.save;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import gizmoball.model.Model;
import gizmoball.view.IGizmoBallView;

public class SaveListener implements ActionListener {
    private Model model;
    private final IGizmoBallView view;

    public SaveListener(Model model, IGizmoBallView view) {
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Saver saver = view.promptSaverType(this.model);
        if(saver != null) {
        	try {
                File file = view.getFileByChooserSave();
                if(file != null) {
                    saver.save(new FileOutputStream(file));
                }
            } catch (FileNotFoundException fnfe) {
                view.displayErrorMessage(fnfe.getMessage(), "File not found");
            }
        }
    }
}
