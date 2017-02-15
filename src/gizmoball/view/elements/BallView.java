package gizmoball.view.elements;

import gizmoball.model.Model;
import physics.Vect;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallView {

    public static void paint(Graphics2D g, Vect ballPos) {
    	g.setColor(Color.YELLOW);
        g.fill(new Ellipse2D.Double(ballPos.x() * Model.L_TO_PIXELS, ballPos.y() * Model.L_TO_PIXELS, Model.L_TO_PIXELS , Model.L_TO_PIXELS));
    }
}
