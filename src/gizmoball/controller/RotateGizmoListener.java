package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.NonRotatableException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class RotateGizmoListener extends MouseAdapter {
    private final BuildModel model;
    private final IBuildView view;

    public RotateGizmoListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/BoardView.L_TO_PIXELS;

        model.select(chosenX, chosenY);
        try {
            model.rotateGizmo();
            view.updateBoard();
        } catch (NonRotatableException e1) {
            view.displayErrorMessage(e1.getMessage(), "Rotate error");
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
