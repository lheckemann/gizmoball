package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

public class DeleteGizmoListener extends MouseAdapter {

    private final BuildModel model;
    private final IBuildView view;

    public DeleteGizmoListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;

        model.select(chosenX, chosenY);
        model.delete();
        view.updateBoard();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseClicked(e);
    }
}
