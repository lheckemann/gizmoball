package gizmoball.view.elements;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

public class AbsorberView {

    public static void paint(Graphics2D graphics, ReadGizmo absorber) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(
                0,
                0,
                absorber.getWidth() * Model.L_TO_PIXELS,
                absorber.getHeight() * Model.L_TO_PIXELS);
    }
}
