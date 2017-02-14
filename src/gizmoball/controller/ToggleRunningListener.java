package gizmoball.controller;

import gizmoball.model.Model;
import gizmoball.view.RunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleRunningListener implements ActionListener {
    private Model model;
    private final RunView view;
    private Timer timer;
    private RunMode currMode;
    public ToggleRunningListener(RunView view) {
        this.view = view;
        currMode = RunMode.RUN;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(currMode)
        {
            case RUN:
                view.changeButtonState();
                currMode = RunMode.STOP;
                break;
            case STOP:
                view.changeButtonState();
                currMode = RunMode.STOP;
                break;
        }
    }
}