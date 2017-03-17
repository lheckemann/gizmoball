package gizmoball.controller.build;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class MoveGizmoListener extends MouseAdapter {
    private final BuildModel model;
    private final IBuildView view;

    private boolean componentSelected;

    public MoveGizmoListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        view.setDisplayLabel("Click on the gizmo/ball you would like to move");
        this.componentSelected = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        if (componentSelected) {
            try {
                model.move(chosenX, chosenY);
                view.updateBoard();
            } catch (PositionOverlapException e1) {
                view.displayErrorMessage("Can't move a gizmo on top of another gizmo or ball", "Position overlap");
            } catch (PositionOutOfBoundsException e1) {
                view.displayErrorMessage("Can't move a gizmo out of bounds", "Position out of bounds error");
            }
            view.setDisplayLabel("Click on the gizmo/ball you would like to move");
            componentSelected = false;
        } else {
            if (model.notEmpty(chosenX, chosenY)) {
                model.select(chosenX, chosenY);
                componentSelected = true;
                view.setDisplayLabel("Click on the area where you would like to move the gizmo/ball to");
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
