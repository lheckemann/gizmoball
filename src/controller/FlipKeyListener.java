package controller;

import model.FlipperModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlipKeyListener implements KeyListener {
    private FlipperModel flipper;

    public FlipKeyListener(FlipperModel flipper) {
        this.flipper = flipper;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            flipper.trigger();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            flipper.untrigger();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
