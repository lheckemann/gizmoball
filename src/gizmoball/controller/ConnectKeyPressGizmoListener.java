package gizmoball.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;

public class ConnectKeyPressGizmoListener extends KeyAdapter implements MouseListener {
    
    private BuildModel model;
    private boolean componentSelected;
    
    public ConnectKeyPressGizmoListener(BuildModel model) {
        this.model = model;
        this.componentSelected = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (componentSelected) {
            model.triggerOnKeyPress(e.getKeyCode());
            componentSelected = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        if (model.notEmpty(chosenX, chosenY)) {
            model.select(chosenX, chosenY);
            componentSelected = true;
        }
        else {
            componentSelected = false;
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

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}
