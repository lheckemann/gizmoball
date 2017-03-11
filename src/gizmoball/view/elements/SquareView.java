package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class SquareView {

    public static void paint(Graphics2D graphics, ReadGizmo square) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(
                0,
                0,
                square.getWidth(),
                square.getHeight());
    }
}
