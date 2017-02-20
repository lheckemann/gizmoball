package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.view.BuildView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeGravityListener implements DocumentListener {
    private final BuildModel model;
    private final BuildView view;

    public ChangeGravityListener(BuildModel model, BuildView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        model.setGravity(Double.parseDouble(view.getGravityText()));
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        model.setGravity(Double.parseDouble(view.getGravityText()));
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }
}
