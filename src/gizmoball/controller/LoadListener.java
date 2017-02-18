package gizmoball.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gizmoball.model.Model;
import gizmoball.model.Loader;
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
            JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
            if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
                new Loader(model).load(new FileInputStream(chooser.getSelectedFile()));
                view.updateBoard();
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, fnfe.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
            //fnfe.printStackTrace();
        } catch (SyntaxError se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "Syntax error", JOptionPane.ERROR_MESSAGE);
            //se.printStackTrace();
        }
    }
}
