package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.BoardView;

import java.awt.*;

public class SquareView {

    public static void paint(Graphics2D graphics, ReadGizmo square) {
        graphics.setColor(Color.green);
        graphics.fillRect(
                0,
                0,
                square.getWidth() * BoardView.L_TO_PIXELS,
                square.getHeight() * BoardView.L_TO_PIXELS);
    }
}
