/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
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
            if (view.promptVelocity()) {
                ball.setVelocityX(view.getPromptedVelocityX());
                ball.setVelocityY(view.getPromptedVelocityY());
            } else {
                this.model.delete();
            }
            
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
