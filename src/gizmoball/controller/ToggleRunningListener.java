package gizmoball.controller;

import gizmoball.model.Model;
import gizmoball.view.RunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleRunningListener implements ActionListener {
    private final RunView view;
    private Timer timer;
    public ToggleRunningListener(ActionListener tickListener, RunView view) {
        timer = new Timer(1000/Model.TICKS_PER_SECOND, tickListener);
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(timer.isRunning()) {
                timer.stop();
                view.changeButtonState();
        } else {
                timer.start();
                view.changeButtonState();
        }
    }
}