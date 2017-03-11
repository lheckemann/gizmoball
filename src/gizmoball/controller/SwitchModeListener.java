package gizmoball.controller;

import gizmoball.view.IGizmoBallView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchModeListener implements ActionListener
{
    private CurrentMode currMode;
    private final IGizmoBallView view;
    public SwitchModeListener(IGizmoBallView view) {
        this.view = view;
        currMode = CurrentMode.BUILD;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(currMode)
        {
            case BUILD:
                view.switchToBuildView();
                currMode = CurrentMode.RUN;
                break;
            case RUN:
                view.switchToRunView();
                currMode = CurrentMode.BUILD;
                break;
        }
    }
}
