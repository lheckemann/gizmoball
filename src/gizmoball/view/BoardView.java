package gizmoball.view;

import gizmoball.model.IModel;
import gizmoball.model.gizmos.ReadGizmo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

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
        return new Dimension(20 * 32, 20 * 32);
    }

    public void updateGUI() {
        Graphics graphics = this.getGraphics();

        //this.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        System.out.println("Works");
        g.translate(getX(), getY());
        AffineTransform origin = g.getTransform();
        for (ReadGizmo gizmo : model.getGizmos()) {
            g.translate(gizmo.getX() * 32, gizmo.getY() * 32);
            g.translate(gizmo.getWidth() / 2.0, gizmo.getHeight() / 2.0);
            g.rotate(gizmo.getRotation().getRadiansFromNorth());
            g.translate(gizmo.getWidth() / -2.0, gizmo.getHeight() / -2.0);
            switch (gizmo.getType()) {
                case LEFT_FLIPPER:
                case RIGHT_FLIPPER:
                    FlipperView.paint(g, gizmo);
                    break;
            }
            g.setTransform(origin);
        }
    }
}