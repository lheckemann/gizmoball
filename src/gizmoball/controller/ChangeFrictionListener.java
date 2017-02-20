package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.view.BuildView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeFrictionListener implements DocumentListener {
    private final BuildModel model;
    private final BuildView view;

    public ChangeFrictionListener(BuildModel model, BuildView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        model.setFriction(Double.parseDouble(view.getFrictionMuText()),
                Double.parseDouble(view.getFrictionMu2Text()));
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        model.setFriction(Double.parseDouble(view.getFrictionMuText()),
                Double.parseDouble(view.getFrictionMu2Text()));
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }
}
