package gizmoball.controller.run;

import gizmoball.model.RunModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyTriggerListener extends KeyAdapter {
    private final RunModel model;
    private final Set<Integer> pressedSet = new HashSet<>();

    public KeyTriggerListener(RunModel model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedSet.contains(e.getKeyCode())) {
            pressedSet.add(e.getKeyCode());
            model.keyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedSet.remove(e.getKeyCode());
        model.keyReleased(e.getKeyCode());
    }
}
