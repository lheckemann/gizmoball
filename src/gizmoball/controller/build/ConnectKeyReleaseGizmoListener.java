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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import gizmoball.model.BuildModel;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;
import gizmoball.view.KeyAndMouseListener;

public class ConnectKeyReleaseGizmoListener extends KeyAdapter implements KeyAndMouseListener {
    private BuildModel model;
    private final IBuildView view;

    private boolean componentSelected;

    public ConnectKeyReleaseGizmoListener(IBuildView view, BuildModel model) {
        this.view = view;
        this.model = model;
        view.setDisplayLabel("Click on the gizmo you want to trigger");

        this.componentSelected = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (componentSelected) {
            model.triggerOnKeyRelease(e.getKeyCode());
            componentSelected = false;
            view.setDisplayLabel("Click on the gizmo you want to trigger");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double chosenX = e.getX()/BoardView.L_TO_PIXELS;
        double chosenY = e.getY()/BoardView.L_TO_PIXELS;
        model.select(chosenX, chosenY);
        if (model.getSelectedGizmo() != null) {
            componentSelected = true;
            view.setDisplayLabel("Press the key you want to trigger the gizmo");
        }
        else {
            componentSelected = false;
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

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
