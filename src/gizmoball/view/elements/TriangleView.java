package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.BoardView;

import java.awt.*;

public class TriangleView {

    private static final int[] xCoords = { 0, BoardView.L_TO_PIXELS, 0},
                               yCoords = { 0, 0, BoardView.L_TO_PIXELS};
    public static void paint(Graphics2D graphics, ReadGizmo triangle) {
        graphics.setColor(Color.red);
        graphics.fillPolygon(xCoords, yCoords, 3);
    }
}
