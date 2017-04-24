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
package gizmoball.view;

import gizmoball.model.ReadBall;
import gizmoball.model.ReadModel;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardView extends JPanel {
    public static final int L_TO_PIXELS = 32;
    private ReadModel model;

    public BoardView(ReadModel model) {
        this.model = model;
    }

    public void paintGizmos(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        AffineTransform originalTransform = g.getTransform();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.scale(L_TO_PIXELS, L_TO_PIXELS);
        // So that lines are still 1 pixel thick
        g.setStroke(new BasicStroke(1.0f/L_TO_PIXELS));
        AffineTransform boardSpaceTransform = g.getTransform();

        for (ReadGizmo gizmo: model.getGizmos()) {

            g.transform(gizmo.getTransform());

            switch (gizmo.getType()) {
                case SQUARE:
                    SquareView.paint(g, gizmo);
                    break;
                case ABSORBER:
                    AbsorberView.paint(g, gizmo);
                    break;
                case TRIANGLE:
                    TriangleView.paint(g, gizmo);
                    break;
                case CIRCLE:
                    CircleView.paint(g, gizmo);
                    break;
                case LEFT_FLIPPER:
                case RIGHT_FLIPPER:
                    FlipperView.paint(g, gizmo);
                    break;
                case LEFT_SPINNING_FLIPPER:
                case RIGHT_SPINNING_FLIPPER:
                    SpinningFlipperView.paint(g, gizmo);
                    break;
                case SPAWNER:
                    SpawnerView.paint(g, gizmo);
                    break;
                case SINK:
                    SinkView.paint(g, gizmo);
                    break;
            }
            g.setTransform(boardSpaceTransform);
        }

        for(ReadBall ball: model.getBalls()) {
            BallView.paint(g, ball);
        }
        g.setTransform(originalTransform);
    }

    public Dimension getPreferredSize() {
        return new Dimension(model.getWidth() * L_TO_PIXELS, model.getHeight() * L_TO_PIXELS);
    }

    protected ReadModel getModel() {
        return model;
    }
}
