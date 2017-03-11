package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

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
        model.rotateGizmo();
        view.updateBoard();
    }
}
