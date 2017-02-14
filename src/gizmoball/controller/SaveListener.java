package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveListener implements ActionListener
{
    //private final Model model;
    //private final GizmoBallView view;
    public SaveListener() {
        //this.model = new Model();
        //this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("save");
    }
}