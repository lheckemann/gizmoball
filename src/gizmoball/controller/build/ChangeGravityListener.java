package gizmoball.controller.build;

import gizmoball.model.BuildModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChangeGravityListener implements ChangeListener {
    private final BuildModel model;
    private final SpinnerNumberModel value;

    public ChangeGravityListener(BuildModel model, SpinnerNumberModel value) {
        this.model = model;
        this.value = value;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        model.setGravity(value.getNumber().doubleValue());
    }
}
