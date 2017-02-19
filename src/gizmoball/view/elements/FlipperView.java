package gizmoball.view.elements;

import gizmoball.model.gizmos.GizmoTypeException;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.view.BoardView;

import java.awt.*;


public class FlipperView {


    public static void paint(Graphics2D graphics, ReadGizmo flipper) {
        graphics.setColor(Color.ORANGE);

        // Rotate (both gizmo rotation and flipper pivot)
        performFlipperTransformations(graphics, flipper);

        // Draw body
         graphics.fillRoundRect(
                 0,
                0,
                flipper.getWidth() * BoardView.L_TO_PIXELS / 4,
                flipper.getHeight() * BoardView.L_TO_PIXELS,
                BoardView.L_TO_PIXELS / 2,
                BoardView.L_TO_PIXELS / 2);

        // Draw pivot
        graphics.setColor(Color.RED);
        graphics.fillOval(
                BoardView.L_TO_PIXELS/8,
                BoardView.L_TO_PIXELS/8,
                BoardView.L_TO_PIXELS/4,
                BoardView.L_TO_PIXELS/4);
    }

    private static void performFlipperTransformations(Graphics2D graphics, ReadGizmo flipper) {
        graphics.translate(flipper.getWidth() * BoardView.L_TO_PIXELS / 2, 0);
        if (flipper.getType().equals(GizmoType.RIGHT_FLIPPER)) {
            graphics.scale(-1, 1);
        }
        graphics.translate(-flipper.getWidth() * BoardView.L_TO_PIXELS / 2, 0);

        // Translate to pivot point
        graphics.translate(0.25 * BoardView.L_TO_PIXELS, 0.25 * BoardView.L_TO_PIXELS);
        try {
            graphics.rotate(-Math.toRadians(flipper.getPivotAngle()));
        } catch (GizmoTypeException e1) {
            assert false : "This Gizmo should have a pivot angle, since it's a flipper";
        }
        // Translate back after rotation
        graphics.translate(-0.25 * BoardView.L_TO_PIXELS, -0.25 * BoardView.L_TO_PIXELS);
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
