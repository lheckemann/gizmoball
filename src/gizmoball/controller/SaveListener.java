package gizmoball.controller;

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
        SaverType saverType = view.getSaveType();
        if (saverType == null) {
            return;
        }
        
        Saver saver;
        switch(view.getSaveType()) {
            case STANDARD:
                saver = new StandardSaver(model);
                break;
            case EXTENDED:
                saver = new Saver(model);
                break;
            default:
                saver = null;
                break;
        }
        
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
