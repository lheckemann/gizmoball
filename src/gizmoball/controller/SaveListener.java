package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import gizmoball.model.Model;
import gizmoball.model.Saver;
import gizmoball.model.ReadModel;

public class SaveListener implements ActionListener {

    private Model model;
    public SaveListener(Model model) {
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
            chooser.setFileFilter(new FileNameExtensionFilter("GizmoBall File", "gzm"));
            if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
                new Saver(model).save(new FileOutputStream(chooser.getSelectedFile()));
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, fnfe.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
            //fnfe.printStackTrace();
        }
    }
}
