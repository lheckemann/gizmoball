package gizmoball.controller;

import gizmoball.model.RunModel;
import gizmoball.view.RunView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickListener implements ActionListener {
    private RunModel model;
    private RunView view;

    public TickListener(RunModel model, RunView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.tick();
        this.view.updateBoard();
    }
}
