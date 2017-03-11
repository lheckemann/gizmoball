package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SpawnerView {

    public static void paint(Graphics2D g, ReadGizmo gizmo) {
        g.setColor(Color.green);
        g.draw(new Ellipse2D.Double(0, 0, 1, 1));
        g.draw(new Ellipse2D.Double(0.25, 0.25, 1.0/2, 1.0/2));
    }
}
