package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.view.BoardView;
import gizmoball.view.BuildView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CreateGizmoListener implements MouseListener, MouseMotionListener {
    private final BuildModel model;
    private final BuildView view;
    private final GizmoType type;
    private double absorberStartX;
    private double absorberStartY;
    private double oldAbsorberStartX;
    private double oldAbsorberStartY;

    public CreateGizmoListener(GizmoType type, BuildView view, BuildModel model) {
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
                    model.addSquare();
                    break;
                case TRIANGLE:
                    model.addTriangle();
                    break;
                case CIRCLE:
                    model.addCircle();
                    break;
                case LEFT_FLIPPER:
                    model.addLeftFlipper();
                    break;
                case RIGHT_FLIPPER:
                    model.addRightFlipper();
                    break;
                default:
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
            this.oldAbsorberStartX = this.absorberStartX;
            this.oldAbsorberStartY = this.absorberStartY;
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        if (type.equals(GizmoType.ABSORBER)) {
            double currentAbsorberEndX = e.getX()/BoardView.L_TO_PIXELS;
            double currentAbsorberEndY = e.getY()/BoardView.L_TO_PIXELS;
            
            System.out.println("current absorber end x: " + currentAbsorberEndX);
            System.out.println("current absorber end y: " + currentAbsorberEndX);
            System.out.println("start x: " + absorberStartX);
            System.out.println("start y: " + absorberStartY);
           
            double startX = Math.min(currentAbsorberEndX, absorberStartX);
            double startY = Math.min(currentAbsorberEndY, absorberStartY);
            double endX = Math.max(currentAbsorberEndX, absorberStartX);
            double endY = Math.max(currentAbsorberEndY, absorberStartY);
            
            System.out.println("actual start x: " + startX);
            System.out.println("actual start y: " + startY);
            System.out.println("actual end x: " + endX);
            System.out.println("actual end y: " + endY);
            
            int width = (int)endX - (int)startX;
            int height = (int)endY - (int)startY;
            System.out.println("width: " + width);
            System.out.println("height: " + height);
            
            model.select(this.oldAbsorberStartX, this.oldAbsorberStartY);
            model.delete();
            model.select(startX, startY);
            this.oldAbsorberStartX = startX;
            this.oldAbsorberStartY = startY;
            try {
                this.model.addAbsorber(width, height);
                this.view.updateBoard();
            } catch (PositionOverlapException e1) {
                
            } catch (PositionOutOfBoundsException e1) {
                
            }
         
        }
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    
}