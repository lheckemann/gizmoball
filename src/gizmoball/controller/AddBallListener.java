package gizmoball.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

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
            view.promptVelocity();
            double velocityX = view.getPromptedVelocityX();
            double velocityY = view.getPromptedVelocityY();

            model.select(ballX, ballY);
            try {
                this.model.addBall(velocityX, velocityY);
                view.updateBoard();
            } catch (PositionOverlapException | PositionOutOfBoundsException e1) {
                // HANDLE EXCEPTION
            }
        }
    }
}
