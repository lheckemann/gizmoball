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

import gizmoball.model.*;
import gizmoball.model.gizmos.*;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;
import gizmoball.view.CustomCursorType;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class CreateGizmoListener implements MouseInputListener {
    private final BuildModel model;
    private final IBuildView view;
    private final GizmoType type;

    private double absorberStartX;
    private double absorberStartY;
    private double oldAbsorberX;
    private double oldAbsorberY;
    private int oldAbsorberWidth;
    private int oldAbsorberHeight;

    private boolean mouseDragged;

    public CreateGizmoListener(GizmoType type, IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        this.type = type;
        this.mouseDragged = false;
        displayDefaultMessage();
        
    }
    
    private void displayDefaultMessage() {
        if (type.equals(GizmoType.ABSORBER)) {
            view.setDisplayLabel("Click & hold the mouse on the grid area you would like to place the absorber");
        } else {
            view.setDisplayLabel("Click on the grid area where you would like to place the gizmo");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double initialX = e.getX()/BoardView.L_TO_PIXELS;
        double initialY = e.getY()/BoardView.L_TO_PIXELS;
        model.select(initialX, initialY);
        try {
            switch (type) {
                case SQUARE:
                    model.addGizmo(new Square());
                    break;
                case TRIANGLE:
                    model.addGizmo(new Triangle());
                    break;
                case CIRCLE:
                    model.addGizmo(new Circle());
                    break;
                case LEFT_FLIPPER:
                    model.addGizmo(new StandardFlipper(true));
                    break;
                case LEFT_SPINNING_FLIPPER:
                    model.addGizmo(new SpinningFlipper(true));
                    break;
                case RIGHT_FLIPPER:
                    model.addGizmo(new StandardFlipper(false));
                    break;
                case RIGHT_SPINNING_FLIPPER:
                    model.addGizmo(new SpinningFlipper(false));
                    break;
                case SPAWNER:
                    model.addGizmo(new Spawner());
                    break;
                case SINK:
                    model.addGizmo(new Sink());
                    break;
            }

            view.updateBoard();
        } catch (PositionOutOfBoundsException positionOutOfBounds){
            if ( ! mouseDragged) {
                view.displayErrorMessage("Can't place a gizmo out of the bounds of the arena", "Position out of bounds");
                this.displayDefaultMessage();
            }
        } catch (PositionOverlapException positionOverlap) {
            if ( ! mouseDragged) {
                view.displayErrorMessage("Can't place a gizmo on top of another gizmo or ball", "Position overlap error");
                this.displayDefaultMessage();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (type.equals(GizmoType.ABSORBER)) {
            this.absorberStartX = e.getX()/BoardView.L_TO_PIXELS;
            this.absorberStartY = e.getY()/BoardView.L_TO_PIXELS;
            this.oldAbsorberX = this.absorberStartX;
            this.oldAbsorberY = this.absorberStartY;
            view.setDisplayLabel("Drag the mouse until you are satifisied with absorber dimensions");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (type.equals(GizmoType.ABSORBER)) {
            double currentAbsorberEndX = e.getX()/BoardView.L_TO_PIXELS;
            double currentAbsorberEndY = e.getY()/BoardView.L_TO_PIXELS;

            double startX = Math.min(currentAbsorberEndX, absorberStartX);
            double startY = Math.min(currentAbsorberEndY, absorberStartY);
            double endX = Math.max(currentAbsorberEndX, absorberStartX);
            double endY = Math.max(currentAbsorberEndY, absorberStartY);

            int width = (int)endX - (int)startX;
            int height = (int)endY - (int)startY;

            model.select(this.oldAbsorberX, this.oldAbsorberY);
            model.delete();
            model.select(startX, startY);
            try {
                this.model.addGizmo(new Absorber(width, height));
                this.oldAbsorberX = startX;
                this.oldAbsorberY = startY;
                this.oldAbsorberWidth = width;
                this.oldAbsorberHeight = height;
                this.view.updateBoard();
            } catch (PositionOverlapException e1) {
                this.drawOldAbsorber();
            } catch (PositionOutOfBoundsException e1) {
                this.drawOldAbsorber();
            } catch (InvalidAbsorberWidthHeight e1) {
                this.drawOldAbsorber();
            }
        }
        else {
            mouseDragged = true;
            mouseClicked(e);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDragged = false;
        this.displayDefaultMessage();
    }

    private void drawOldAbsorber() {
        this.model.select(this.oldAbsorberX, this.oldAbsorberY);
        try {
            this.model.addGizmo(new Absorber(this.oldAbsorberWidth, this.oldAbsorberHeight));
        } catch (PositionOverlapException | PositionOutOfBoundsException | InvalidAbsorberWidthHeight e) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        view.setCursor(CustomCursorType.GIZMO);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        view.setCursor(CustomCursorType.DEFAULT);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
