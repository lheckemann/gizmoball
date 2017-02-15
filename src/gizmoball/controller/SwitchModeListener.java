package gizmoball.controller;

import gizmoball.model.IModel;
import gizmoball.view.GizmoBallView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchModeListener implements ActionListener
{
    private CurrentMode currMode;
    private final GizmoBallView view;
    private IModel model;
    public SwitchModeListener(GizmoBallView view, IModel model) {
        this.view = view;
        this.model = model;
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
                view.switchToBuildView(this.model);
                currMode = CurrentMode.RUN;
                break;
            case RUN:
                view.switchToRunView(this.model);
                currMode = CurrentMode.BUILD;
                break;
        }
    }
}