package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.model.RunModel;
import gizmoball.view.GizmoBallView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchModeListener implements ActionListener
{
    private CurrentMode currMode;
    private final GizmoBallView view;
    public SwitchModeListener(GizmoBallView view) {
        this.view = view;
        currMode = CurrentMode.RUN;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //
        // TODO: don't create new Model every time
        //
        switch(currMode)
        {
            case BUILD:
                BuildModel newModelB = new Model(20, 20); // fake model interaction
                view.switchToBuildView(newModelB);
                currMode = CurrentMode.RUN;
                break;
            case RUN:
                RunModel newModelR = new Model(20, 20d);
                view.switchToRunView(newModelR);
                currMode = CurrentMode.BUILD;
                break;
        }
    }
}