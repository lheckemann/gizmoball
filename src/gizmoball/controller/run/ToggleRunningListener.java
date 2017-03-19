package gizmoball.controller.run;

import gizmoball.view.IRunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleRunningListener implements ActionListener {
    private Timer timer;
    private final IRunView view;

    public ToggleRunningListener(Timer timer, IRunView view) {
        this.timer = timer;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timer.isRunning()) {
            timer.stop();
            view.changeButtonState();
        } else {
            timer.start();
            view.changeButtonState();
        }
    }
}
