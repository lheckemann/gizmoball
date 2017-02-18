package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import gizmoball.model.Model;
import gizmoball.model.ReadModel;

public class SaveListener implements ActionListener
{

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
                //If the file exists just write to the existing file
                if (chooser.getSelectedFile().exists()) {
                    model.save(new FileOutputStream(chooser.getSelectedFile()));
                } //Otherwise add the .gzm extension and write to a new file
                else {
                    model.save(new FileOutputStream(new File(chooser.getSelectedFile().toString() + ".gzm")));
                }
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, fnfe.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
            //fnfe.printStackTrace();
        } 
    }
}
