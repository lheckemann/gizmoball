/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
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
