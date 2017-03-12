package gizmoball.controller;

import gizmoball.model.*;
import gizmoball.model.gizmos.*;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.view.BoardView;
import gizmoball.view.IBuildView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;

public class CreateGizmoListener extends MouseAdapter implements MouseMotionListener {
    private final BuildModel model;
    private final IBuildView view;
    private final GizmoType type;
    private double absorberStartX;
    private double absorberStartY;
    private double oldAbsorberX;
    private double oldAbsorberY;
    private int oldAbsorberWidth;
    private int oldAbsorberHeight;

    public CreateGizmoListener(GizmoType type, IBuildView view, BuildModel model) {
        this.model = model;
        this.view = view;
        this.type = type;
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

        } catch (PositionOverlapException positionOverlap) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (type.equals(GizmoType.ABSORBER)) {
            this.absorberStartX = e.getX()/BoardView.L_TO_PIXELS;
            this.absorberStartY = e.getY()/BoardView.L_TO_PIXELS;
            this.oldAbsorberX = this.absorberStartX;
            this.oldAbsorberY = this.absorberStartY;
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
            }
        }
        else mouseClicked(e);
    }

    private void drawOldAbsorber() {
        this.model.select(this.oldAbsorberX, this.oldAbsorberY);
        try {
            this.model.addGizmo(new Absorber(this.oldAbsorberWidth, this.oldAbsorberHeight));
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
