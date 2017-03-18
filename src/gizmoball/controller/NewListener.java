package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.Model;
import gizmoball.view.IGizmoBallView;

public class NewListener implements ActionListener {

    private Model model;
    private IGizmoBallView view;
    public NewListener(Model model, IGizmoBallView gizmoBallView) {
        this.model = model;
        this.view = gizmoBallView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.reset();
        this.view.reset();
    }
}
