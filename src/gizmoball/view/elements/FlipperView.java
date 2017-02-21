package gizmoball.view.elements;

import gizmoball.model.gizmos.GizmoTypeException;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.view.BoardView;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;


public class FlipperView {


    public static void paint(Graphics2D graphics, ReadGizmo flipper) {
        graphics.setColor(Color.ORANGE);

        // Rotate (both gizmo rotation and flipper pivot)
        performFlipperTransformations(graphics, flipper);

        // Draw body
        Shape body = new RoundRectangle2D.Double(0, 0, flipper.getWidth()/4.0, flipper.getHeight(), 0.5, 0.5);
        graphics.fill(body);

        // Draw pivot
        graphics.setColor(Color.RED);
        Shape pivot = new Ellipse2D.Double(1.0/8, 1.0/8, 1.0/4, 1.0/4);
        graphics.fill(pivot);
    }

    private static void performFlipperTransformations(Graphics2D graphics, ReadGizmo flipper) {
        graphics.translate(flipper.getWidth() / 2, 0);
        if (flipper.getType().equals(GizmoType.RIGHT_FLIPPER)) {
            graphics.scale(-1, 1);
        }
        graphics.translate(-flipper.getWidth() / 2, 0);

        // Translate to pivot point
        graphics.translate(0.25, 0.25);
        try {
            graphics.rotate(-Math.toRadians(flipper.getPivotAngle()));
        } catch (GizmoTypeException e1) {
            assert false : "This Gizmo should have a pivot angle, since it's a flipper";
        }
        // Translate back after rotation
        graphics.translate(-0.25, -0.25);
    }
}


/*
 *
 * North left
 * |.       \.          --
 * |.  ->   .\    ->    ..
 *
 * East left:
 * --        ./         .|
 * ..  ->    /.    ->   .|
 *
 * South left
 * .|       \.          ..
 * .|   ->  .\     ->   --
 *
 *
 * West left
 * ..         ./        |.
 * --    ->   /.   ->   |.
 *
 * North right
 * .|       ./          --
 * .|   ->  /.     ->   ..
 *
 * East right
 * ..       \.          .|
 * __   ->  .\     ->   .|
 *
 * South right
 * |.       ./          ..
 * |.   ->  /      ->   --
 *
 * West right
 * --       \.          |.
 * ..   ->  .\     ->   |.
 */
