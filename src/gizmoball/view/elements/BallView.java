package gizmoball.view.elements;

import gizmoball.model.ReadBall;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class BallView {

    public static void paint(Graphics2D g, ReadBall ball) {
        g.setColor(Color.YELLOW);
        double radius = ball.getRadius();
        g.fill(new Ellipse2D.Double(
                ball.getX() - radius,
                ball.getY() - radius,
                radius*2,
                radius*2));

        // Draw velocity vector
        g.setColor(Color.RED);
        g.draw(new Line2D.Double(ball.getX(), ball.getY(),
                ball.getX() + ball.getVelocityX() / 10,
                ball.getY() + ball.getVelocityY() / 10));
    }
}
