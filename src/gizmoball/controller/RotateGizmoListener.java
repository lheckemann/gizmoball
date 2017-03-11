package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

public class RotateGizmoListener implements MouseListener {

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
