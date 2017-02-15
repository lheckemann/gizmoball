package gizmoball.view;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class CircleView {
    public static void paint(Graphics2D graphics, ReadGizmo circle) {
        graphics.setColor(Color.pink);

        graphics.fillOval(circle.getX() * Model.L_TO_PIXELS, circle.getY() * Model.L_TO_PIXELS, circle.getWidth() * Model.L_TO_PIXELS, circle.getHeight() * Model.L_TO_PIXELS);

    }
}
