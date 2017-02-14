package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.model.SyntaxError;
import gizmoball.view.GizmoBallView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadListener implements ActionListener
{
    private final BuildModel model;
    private final GizmoBallView view;

    public LoadListener(BuildModel model, GizmoBallView view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(new JFrame());
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = chooser.getSelectedFile();
                model.load(new FileInputStream(selectedFile.getPath()));
            }


        }
        catch (FileNotFoundException|SyntaxError ex)
        {
            ex.printStackTrace();
        }
    }
}