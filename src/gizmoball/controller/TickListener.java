package gizmoball.controller;

import gizmoball.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickListener implements ActionListener {
    private Model model;
    public TickListener(Model model) {
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        model.tick();
    }
}
