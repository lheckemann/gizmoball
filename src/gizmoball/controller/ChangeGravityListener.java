package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.view.IBuildView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeGravityListener implements DocumentListener {
    private final BuildModel model;
    private final IBuildView view;

    public ChangeGravityListener(BuildModel model, IBuildView view) {
        this.model = model;
        this.view = view;
    }

    private void setNewGravity() {
        String grav = view.getGravityText();
        if(!grav.equals(""))
            model.setGravity(Double.parseDouble(grav));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setNewGravity();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setNewGravity();
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }
}
