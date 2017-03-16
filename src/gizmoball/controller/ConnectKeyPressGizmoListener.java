package gizmoball.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class ConnectKeyPressGizmoListener extends KeyAdapter implements MouseListener {
    private BuildModel model;
    private final IBuildView view;

    private boolean componentSelected;
    
    public ConnectKeyPressGizmoListener(IBuildView view, BuildModel model) {
        this.view = view;
        this.model = model;
        this.view.setDisplayLabel("Click on the gizmo you want to trigger");
        this.componentSelected = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (componentSelected) {
            model.triggerOnKeyPress(e.getKeyCode());
            componentSelected = false;
            view.setDisplayLabel("Click on the gizmo you want to trigger");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        if (model.notEmpty(chosenX, chosenY)) {
            model.select(chosenX, chosenY);
            componentSelected = true;
            view.setDisplayLabel("Press the key you want to trigger the gizmo");
        }
        else {
            componentSelected = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        view.setCursor(CustomCursorType.OPERATIONS);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        view.setCursor(CustomCursorType.DEFAULT);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
