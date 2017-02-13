package gizmoball.controller;

import gizmoball.model.RunModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickListener implements ActionListener {
    private RunModel model;
    private JComponent view;
    public TickListener(RunModel model, JComponent view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.tick();
        view.updateUI(); // TODO: is this the right method to call? It does make the movement smooth.
    }
}
