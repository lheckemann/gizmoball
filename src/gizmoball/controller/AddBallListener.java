package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

public class AddBallListener implements MouseListener {

    private IBuildView view;
    private BuildModel model;
    public AddBallListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
       view.promptVelocity();
       double velocityX = view.getPromptedVelocityX();
       double velocityY = view.getPromptedVelocityY();
       double ballX = e.getX()/(double)BoardView.L_TO_PIXELS;
       double ballY = e.getY()/(double)BoardView.L_TO_PIXELS;
       model.select(ballX, ballY);
       try {
           this.model.addBall(velocityX, velocityY);
           view.updateBoard();
       } catch (PositionOverlapException | PositionOutOfBoundsException e1) {
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
