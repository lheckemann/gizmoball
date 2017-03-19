package gizmoball.controller.build;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

import javax.swing.event.MouseInputListener;

public class DeleteGizmoListener extends MouseAdapter implements MouseInputListener {
    private final BuildModel model;
    private final IBuildView view;

    public DeleteGizmoListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        view.setDisplayLabel("Click on the gizmo/ball you would like to delete");
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

    @Override
    public void mouseEntered(MouseEvent e) {
        view.setCursor(CustomCursorType.OPERATIONS);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        view.setCursor(CustomCursorType.DEFAULT);
    }
}