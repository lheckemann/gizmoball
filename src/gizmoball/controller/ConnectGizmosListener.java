package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

public class ConnectGizmosListener implements MouseListener {

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
