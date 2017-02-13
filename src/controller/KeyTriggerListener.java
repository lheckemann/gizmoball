package controller;

import model.ReadModel;
import model.RunModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTriggerListener extends KeyAdapter {
    private final RunModel model;
    public KeyTriggerListener(RunModel model) {
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
