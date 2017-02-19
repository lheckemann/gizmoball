package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.BoardView;

import java.awt.*;

public class CircleView {
    public static void paint(Graphics2D graphics, ReadGizmo circle) {
        graphics.setColor(Color.pink);
        graphics.fillOval(
                0,
                0,
                circle.getWidth() * BoardView.L_TO_PIXELS,
                circle.getHeight() * BoardView.L_TO_PIXELS);
    }
}
