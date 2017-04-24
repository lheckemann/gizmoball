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
import java.awt.geom.Line2D;

public class SinkView {
    public static void paint(Graphics2D g, ReadGizmo gizmo) {
        g.setColor(Color.RED);
        g.draw(new Ellipse2D.Double(0, 0, 1, 1));
        g.draw(new Ellipse2D.Double(0.25, 0.25, 1.0/2, 1.0/2));
        g.draw(new Line2D.Double(0.5, 0, 0.5, 0.25));
        g.draw(new Line2D.Double(0, 0.5, 0.25, 0.5));
        g.draw(new Line2D.Double(0.5, 1, 0.5, 0.75));
        g.draw(new Line2D.Double(1, 0.5, 0.75, 0.5));
    }
}
