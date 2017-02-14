package gizmoball.view;

import com.sun.org.apache.xpath.internal.operations.Mod;
import gizmoball.model.Model;
import gizmoball.model.gizmos.GizmoTypeException;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

/**
 * Created by fra_m on 14/02/2017.
 */
public class SquareView {

    public static void paint(Graphics2D graphics, ReadGizmo square) {
        graphics.setColor(Color.green);

        graphics.fillRect(square.getX() * Model.L_TO_PIXELS , square.getY() * Model.L_TO_PIXELS, square.getWidth() * Model.L_TO_PIXELS, square.getHeight() * Model.L_TO_PIXELS);

    }
}
