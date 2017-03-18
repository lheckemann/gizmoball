package gizmoball.controller.build;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;
import gizmoball.view.KeyAndMouseListener;

public class ConnectKeyReleaseGizmoListener extends KeyAdapter implements KeyAndMouseListener {
    private BuildModel model;
    private final IBuildView view;

    private boolean componentSelected;

    public ConnectKeyReleaseGizmoListener(IBuildView view, BuildModel model) {
        this.view = view;
        this.model = model;
        view.setDisplayLabel("Click on the gizmo you want to trigger");

        this.componentSelected = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (componentSelected) {
            model.triggerOnKeyRelease(e.getKeyCode());
            componentSelected = false;
            view.setDisplayLabel("Click on the gizmo you want to trigger");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/BoardView.L_TO_PIXELS;
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
