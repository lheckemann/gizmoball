package gizmoball.view;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class SquareView {

    public static void paint(Graphics2D graphics, ReadGizmo square) {
        graphics.setColor(Color.green);

        graphics.fillRect(square.getX() * Model.L_TO_PIXELS , square.getY() * Model.L_TO_PIXELS, square.getWidth() * Model.L_TO_PIXELS, square.getHeight() * Model.L_TO_PIXELS);

    }
}
