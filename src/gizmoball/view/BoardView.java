package gizmoball.view;

import gizmoball.model.ReadModel;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.elements.*;
import physics.Vect;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private ReadModel model;

    public BoardView(ReadModel model) {
        this.model = model;
        this.setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }

    public void paintGizmos(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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

    public Dimension getPreferredSize() {
        return new Dimension(20 * ReadModel.L_TO_PIXELS, 20 * ReadModel.L_TO_PIXELS);
    }
}
