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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.CustomCursorType;
import gizmoball.view.IBuildView;

public class ConnectOuterwallListener extends MouseAdapter {

    private final BuildModel model;
    private final IBuildView view;

    public ConnectOuterwallListener(IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        view.setDisplayLabel("Click the gizmo you want to trigger");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/(double)BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/(double)BoardView.L_TO_PIXELS;
        model.select(chosenX, chosenY);
        if (model.getSelectedGizmo() != null) {
            model.triggerOnOuterWalls();
            view.updateBoard();
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
