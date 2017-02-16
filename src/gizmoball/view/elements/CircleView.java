package gizmoball.view.elements;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class CircleView {
    public static void paint(Graphics2D graphics, ReadGizmo circle) {
        graphics.setColor(Color.pink);
        graphics.fillOval(
                0,
                0,
                circle.getWidth() * Model.L_TO_PIXELS,
                circle.getHeight() * Model.L_TO_PIXELS);
    }
}
