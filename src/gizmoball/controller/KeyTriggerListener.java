package gizmoball.controller;

import gizmoball.model.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyTriggerListener extends KeyAdapter {
    Model model;

    public KeyTriggerListener(Model model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        model.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        model.keyReleased(e.getKeyCode());
    }
}
