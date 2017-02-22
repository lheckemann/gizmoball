package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

public class NewListener implements ActionListener {

    private Model model;
    private GizmoBallView view;
    public NewListener(Model model, GizmoBallView gizmoBallView) {
        this.model = model;
        this.view = gizmoBallView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.reset();
        this.view.updateBoard();
    }

}
