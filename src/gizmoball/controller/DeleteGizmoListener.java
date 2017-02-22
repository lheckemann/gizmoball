package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.view.BoardView;
import gizmoball.view.BuildView;

public class DeleteGizmoListener implements MouseListener {

    private final BuildModel model;
    private final BuildView view;

    public DeleteGizmoListener(BuildView view, BuildModel model) {
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
