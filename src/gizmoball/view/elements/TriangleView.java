package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class TriangleView {

    private static final int[] xCoords = { 0, 1, 0},
                               yCoords = { 0, 0, 1};
    public static void paint(Graphics2D graphics, ReadGizmo triangle) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillPolygon(xCoords, yCoords, 3);
    }
}
