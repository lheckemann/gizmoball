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

    private void setNewFriction() {
        String mu = view.getFrictionMuText();
        String mu2 = view.getFrictionMu2Text();
        if(!mu.equals("") && !mu2.equals(""))
            model.setFriction(Double.parseDouble(view.getFrictionMuText()),
                    Double.parseDouble(view.getFrictionMu2Text()));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setNewFriction();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setNewFriction();
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }
}
