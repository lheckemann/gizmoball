package gizmoball.controller;

import gizmoball.model.ReadModel;
import gizmoball.view.IRunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleRunningListener implements ActionListener {
    private final IRunView view;
    private Timer timer;

    public ToggleRunningListener(ActionListener tickListener, IRunView view) {
        timer = new Timer((int) (1000*ReadModel.SECONDS_PER_TICK), tickListener);
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
