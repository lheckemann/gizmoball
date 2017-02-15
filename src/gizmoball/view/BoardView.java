package gizmoball.view;

import gizmoball.model.IModel;
import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;
import physics.Vect;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private IModel model;

    public BoardView(IModel model) {
        this.model = model;

        this.setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Dimension getPreferredSize() {
        return new Dimension(20 * Model.L_TO_PIXELS, 20 * Model.L_TO_PIXELS);
    }

    public void updateGUI() {
        Graphics graphics = this.getGraphics();

        Graphics2D g = (Graphics2D) graphics;
        for (ReadGizmo gizmo : model.getGizmos()) {
            switch (gizmo.getType()) {
                case SQUARE:
                	SquareView.paint(g, gizmo);
                    break;
                case ABSORBER:
                    AbsorberView.paint(g, gizmo);
                    break;
                case TRIANGLE:
                    TriangleView.paint(g, gizmo);
                    break;
                case CIRCLE:
                    CircleView.paint(g, gizmo);
                    break;
                case LEFT_FLIPPER:
                case RIGHT_FLIPPER:
                    FlipperView.paint(g, gizmo);
                    break;
            }
        }

        for(Vect ballPos: model.getBallPositions()) {
            BallView.paint(g, ballPos);
        }
    }
}