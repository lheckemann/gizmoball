package gizmoball.view.elements;

import gizmoball.model.ReadBall;
import gizmoball.view.BoardView;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallView {

    public static void paint(Graphics2D g, ReadBall ball) {
        g.setColor(Color.YELLOW);
        double radius = BoardView.L_TO_PIXELS * ball.getRadius();
        g.fill(new Ellipse2D.Double(
                ball.getX() * BoardView.L_TO_PIXELS - radius,
                ball.getY() * BoardView.L_TO_PIXELS - radius,
                radius*2,
                radius*2));
    }
}
