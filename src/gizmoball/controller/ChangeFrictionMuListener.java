package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.view.IBuildView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeFrictionMuListener implements ChangeListener {
    private final BuildModel model;
    private final SpinnerNumberModel value;

    public ChangeFrictionMuListener(BuildModel model, SpinnerNumberModel value) {
        this.model = model;
        this.value = value;
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        model.setFriction(value.getNumber().doubleValue(), model.getFrictionMu2());
    }
}
