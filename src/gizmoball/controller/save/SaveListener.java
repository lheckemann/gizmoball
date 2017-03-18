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
        view.promptSaveType(model);       
    }
    
    public void saveStandard() {
        this.save(new StandardSaver(this.model));
    }
    
    public void saveExtended() {
        this.save(new Saver(this.model));
    }
    
    private void save(Saver saver) {

        try {
            File newFile = view.getFileByChooserSave();
            if(newFile != null) {  
                saver.save(new FileOutputStream(newFile));
            }
        } catch (FileNotFoundException fnfe) {
            view.displayErrorMessage(fnfe.getMessage(), "File not found");
        }
    }
}
