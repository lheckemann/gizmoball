package gizmoball.view;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;

/**
 * Created by fra_m on 14/02/2017.
 */
public class AbsorberView {

    public static void paint(Graphics2D graphics, ReadGizmo absorber) {
        System.out.println(absorber.getX());
        System.out.println(absorber.getY());
        System.out.println(absorber.getHeight());
        System.out.println(absorber.getWidth());
        graphics.setColor(Color.green);

        switch (absorber.getRotation()) {
            case EAST:
                graphics.rotate(Math.toRadians(90));
                break;
            case SOUTH:
                graphics.rotate(Math.toRadians(180));
                break;
            case WEST:
                graphics.rotate(Math.toRadians(270));
                break;
            default:
                break;
        }

        graphics.fillRect(absorber.getX() * Model.L_TO_PIXELS , absorber.getY() * Model.L_TO_PIXELS, absorber.getWidth() * Model.L_TO_PIXELS, absorber.getHeight() * Model.L_TO_PIXELS);
    }
}
