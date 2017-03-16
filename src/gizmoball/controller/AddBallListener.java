package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class AddBallListener extends MouseAdapter {
    private IBuildView view;
    private BuildModel model;

    public AddBallListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double ballX = e.getX() / (double) BoardView.L_TO_PIXELS;
        double ballY = e.getY() / (double) BoardView.L_TO_PIXELS;

        if(!model.notEmpty(ballX, ballY)) {
        
            model.select(ballX, ballY);
            try {
                view.promptVelocity();
                double velocityX = view.getPromptedVelocityX();
                double velocityY = view.getPromptedVelocityY();
                this.model.addBall(velocityX, velocityY);
                view.updateBoard();
            } catch (PositionOverlapException e1) {
                view.displayErrorMessage("Can't place a ball on top of another ball or gizmo", "Position overlap");
            } catch (PositionOutOfBoundsException e1) {
                view.displayErrorMessage(e1.getMessage(), "Position out of bounds");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        view.setCursor(CustomCursorType.BALL);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        view.setCursor(CustomCursorType.DEFAULT);
    }
}
