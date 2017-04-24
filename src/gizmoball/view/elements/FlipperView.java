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

import gizmoball.model.gizmos.ReadGizmo;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;


public class FlipperView {
    public static void paint(Graphics2D graphics, ReadGizmo flipper) {
        graphics.setColor(Color.BLUE);

        // Draw body
        Shape body = new RoundRectangle2D.Double(0, 0, flipper.getWidth()/4.0, flipper.getHeight(), 0.5, 0.5);
        graphics.fill(body);

        // Draw pivot
        graphics.setColor(Color.GREEN);
        Shape pivot = new Ellipse2D.Double(1.0/8, 1.0/8, 1.0/4, 1.0/4);
        graphics.fill(pivot);
    }
}
