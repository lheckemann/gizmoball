package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;


public class FlipperView {
    public static void paint(Graphics2D graphics, ReadGizmo flipper) {
        graphics.setColor(Color.BLUE);

        // Draw body
        Shape body = new RoundRectangle2D.Double(0, 0, flipper.getWidth()/4.0, flipper.getHeight(), 0.5, 0.5);
        graphics.fill(body);

        // Draw pivot
        graphics.setColor(Color.GREEN);
        Shape pivot = new Ellipse2D.Double(1.0/8, 1.0/8, 1.0/4, 1.0/4);
        graphics.fill(pivot);
    }
}
