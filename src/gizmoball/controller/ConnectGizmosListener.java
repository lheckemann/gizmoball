package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class ConnectGizmosListener extends MouseAdapter {
    private final BuildModel model;
    private final IBuildView view;

    private boolean componentSelected;

    public ConnectGizmosListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;

        this.componentSelected = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        if (componentSelected) {
            model.triggerOnGizmoAt(chosenX, chosenY);
            view.updateBoard();
            componentSelected = false;
        } else {
            if (model.notEmpty(chosenX, chosenY)) {
                model.select(chosenX, chosenY);
                componentSelected = true;
            }
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
}
