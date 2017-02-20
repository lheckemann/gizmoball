package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gizmoball.model.Model;
import gizmoball.model.SyntaxError;
import gizmoball.view.GizmoBallView;

public class LoadListener implements ActionListener {
    private final Model model;
    private final GizmoBallView view;

    public LoadListener(Model model, GizmoBallView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            model.load(new FileInputStream(view.getFileByChooser()));
            view.updateBoard();
        } catch (FileNotFoundException fnfe) {
            view.displayErrorMessage(fnfe.getMessage(), "File not found");
            //fnfe.printStackTrace();
        } catch (SyntaxError se) {
            view.displayErrorMessage(se.getMessage(), "File not found");
            //se.printStackTrace();
        }
    }
}
