package gizmoball.controller;

import gizmoball.view.BuildView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleGizmoChoiceListener implements ActionListener
{
    private BuildView view;
    public ToggleGizmoChoiceListener(BuildView view) {
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        view.toggleGizmoChoiceVisibility();
    }
}