package gizmoball.view.elements;

import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.BoardView;

import java.awt.*;

public class AbsorberView {

    public static void paint(Graphics2D graphics, ReadGizmo absorber) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(
                0,
                0,
                absorber.getWidth() * BoardView.L_TO_PIXELS,
                absorber.getHeight() * BoardView.L_TO_PIXELS);
    }
}
