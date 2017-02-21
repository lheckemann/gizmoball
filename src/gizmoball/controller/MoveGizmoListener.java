package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.BuildView;

public class MoveGizmoListener implements MouseListener {

    private final BuildModel model;
    private final BuildView view;
    private boolean componentSelected;

    public MoveGizmoListener(BuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        this.componentSelected = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/BoardView.L_TO_PIXELS;
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

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
