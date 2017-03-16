package gizmoball.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.CustomCursorType;
import gizmoball.view.IBuildView;

public class ConnectOuterwallListener extends MouseAdapter {

    private final BuildModel model;
    private final IBuildView view;

    public ConnectOuterwallListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        if (model.notEmpty(chosenX, chosenY)) {
            model.select(chosenX, chosenY);
            model.triggerOnOuterWalls();
            view.updateBoard();
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