package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

public class MoveGizmoListener extends MouseAdapter {

    private final BuildModel model;
    private final IBuildView view;
    private boolean componentSelected;

    public MoveGizmoListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
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
            } catch (PositionOverlapException | PositionOutOfBoundsException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            componentSelected = false;
        } else {
            if (model.notEmpty(chosenX, chosenY)) {
                model.select(chosenX, chosenY);
                componentSelected = true;
            }
        }
    }
}
