package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;
import java.awt.geom.Line2D;

public class SinkView {
    public static void paint(Graphics2D g, ReadGizmo gizmo) {
        g.setColor(Color.MAGENTA);
        g.drawOval(0, 0, 1, 1);
        g.setColor(Color.red);
        g.draw(new Line2D.Double(0.5, 0, 0.5, 1));
        g.draw(new Line2D.Double(0, 0.5, 1, 0.5));
    }
}
