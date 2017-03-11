package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class CircleView {
    public static void paint(Graphics2D graphics, ReadGizmo circle) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillOval(
                0,
                0,
                circle.getWidth(),
                circle.getHeight());
    }
}
