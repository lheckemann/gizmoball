package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SinkView {
    public static void paint(Graphics2D g, ReadGizmo gizmo) {
        g.setColor(Color.red);
        g.draw(new Ellipse2D.Double(0, 0, 1, 1));
        g.draw(new Ellipse2D.Double(0.25, 0.25, 1.0/2, 1.0/2));
        g.draw(new Line2D.Double(0.5, 0, 0.5, 0.25));
        g.draw(new Line2D.Double(0, 0.5, 0.25, 0.5));
        g.draw(new Line2D.Double(0.5, 1, 0.5, 0.75));
        g.draw(new Line2D.Double(1, 0.5, 0.75, 0.5));
    }
}
