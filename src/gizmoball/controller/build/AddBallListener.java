package gizmoball.controller.build;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import gizmoball.model.*;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class AddBallListener extends MouseAdapter {
    private IBuildView view;
    private BuildModel model;

    public AddBallListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        view.setDisplayLabel("Click on a grid location to add a ball");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double ballX = e.getX() / (double) BoardView.L_TO_PIXELS;
        double ballY = e.getY() / (double) BoardView.L_TO_PIXELS;
        model.select(ballX, ballY);
        Ball ball = new Ball();
        try {
            this.model.addBall(ball);
            view.setDisplayLabel("Enter the desired ball velocity");
            view.promptVelocity();
            ball.setVelocityX(view.getPromptedVelocityX());
            ball.setVelocityY(view.getPromptedVelocityY());
            view.updateBoard();
            view.setDisplayLabel("Click on a grid location to add a ball");
        } catch (PositionOverlapException e1) {
            view.displayErrorMessage("Can't place a ball on top of another ball or gizmo", "Position overlap");
            view.setDisplayLabel("Click on a grid location to add a ball");
        } catch (PositionOutOfBoundsException e1) {
            view.displayErrorMessage("Can't place a ball out of the bounds of the arena", "Position out of bounds");
            view.setDisplayLabel("Click on a grid location to add a ball");
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
