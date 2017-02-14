package gizmoball.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.BuildModel;

public class BuildBoardView extends JComponent {
    private final BuildModel model;
    private final FlipperView flipperView = new FlipperView();

    public BuildBoardView(BuildModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.translate(getX(), getY());
        AffineTransform origin = g.getTransform();
        for (ReadGizmo gizmo : model.getGizmos()) {
            g.translate(gizmo.getX() * Model.L_TO_PIXELS, gizmo.getY() * Model.L_TO_PIXELS);
            g.translate(gizmo.getWidth() / 2.0, gizmo.getHeight() / 2.0);
            g.rotate(gizmo.getRotation().getRadiansFromNorth());
            g.translate(gizmo.getWidth() / -2.0, gizmo.getHeight() / -2.0);
            switch (gizmo.getType()) {
                case LEFT_FLIPPER:
                case RIGHT_FLIPPER:
                    flipperView.paint(g, gizmo);
            }
            g.setTransform(origin);
        }
    }
}
