package gizmoball.view;

import gizmoball.model.Model;
import gizmoball.model.ReadBall;
import gizmoball.model.ReadModel;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.elements.*;
import physics.Vect;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Map;

public class BoardView extends JPanel {
    public static final int L_TO_PIXELS = 32;
    private ReadModel model;

    public BoardView(ReadModel model) {
        this.model = model;
    }

    public void paintGizmos(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform originalTransform = g.getTransform();

        for (ReadGizmo gizmo: model.getGizmos()) {

            // Translate to gizmo's position
            g.translate(gizmo.getX() * L_TO_PIXELS, gizmo.getY() * L_TO_PIXELS);

            // Apply gizmo rotation
            g.translate(
                    gizmo.getWidth() * L_TO_PIXELS / 2,
                    gizmo.getHeight() * L_TO_PIXELS / 2);
            g.rotate(gizmo.getRotation().getRadiansFromNorth());
            g.translate(
                    -gizmo.getWidth() * L_TO_PIXELS / 2,
                    -gizmo.getHeight() * L_TO_PIXELS / 2);

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
            g.setTransform(originalTransform);
        }

        for(ReadBall ball: model.getBalls()) {
            BallView.paint(g, ball);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(model.getWidth() * L_TO_PIXELS, model.getHeight() * L_TO_PIXELS);
    }

    protected ReadModel getModel() {
        return model;
    }
}
