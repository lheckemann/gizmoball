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

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

public class MoveGizmoListener extends MouseAdapter {
    private final BuildModel model;
    private final IBuildView view;

    private boolean componentSelected;

    public MoveGizmoListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        view.setDisplayLabel("Click on the gizmo/ball you would like to move");
        this.componentSelected = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        if (componentSelected) {
            try {
                model.move(chosenX, chosenY);
                view.updateBoard();
            } catch (PositionOverlapException e1) {
                if (model.getSelectedGizmo() != null) {
                    view.displayErrorMessage("Can't move a gizmo on top of another gizmo or ball", "Position overlap");
                } else {
                    view.displayErrorMessage("Can't move a ball on top of another gizmo or ball", "Position overlap");
                }
            } catch (PositionOutOfBoundsException e1) {
                view.displayErrorMessage("Can't move a gizmo out of bounds", "Position out of bounds error");
            }
            view.setDisplayLabel("Click on the gizmo/ball you would like to move");
            componentSelected = false;
        } else {
            model.select(chosenX, chosenY);
            if (model.getSelectedGizmo() != null || model.getSelectedBall() != null) {
                componentSelected = true;
                view.setDisplayLabel("Click on the area where you would like to move the gizmo/ball to");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        view.setCursor(CustomCursorType.OPERATIONS);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        view.setCursor(CustomCursorType.DEFAULT);
    }
}
