package gizmoball.view.elements;

import gizmoball.model.Model;
import gizmoball.model.ReadBall;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallView {

    public static void paint(Graphics2D g, ReadBall ball) {
    	g.setColor(Color.YELLOW);
        g.fill(new Ellipse2D.Double(ball.getX() * Model.L_TO_PIXELS, ball.getY() * Model.L_TO_PIXELS, Model.L_TO_PIXELS , Model.L_TO_PIXELS));
    }
}
