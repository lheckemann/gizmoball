package gizmoball.view.elements;

import gizmoball.model.Model;
import gizmoball.model.gizmos.GizmoTypeException;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class FlipperView {


	public static void paint(Graphics2D graphics, ReadGizmo flipper) {
    	graphics.setColor(Color.ORANGE);
    	AffineTransform originalTransform = graphics.getTransform();

        // Translate to flipper's top-left corner
        graphics.translate(flipper.getX() * Model.L_TO_PIXELS, flipper.getY() * Model.L_TO_PIXELS);

        // Rotate (both gizmo rotation and flipper pivot)
    	performFlipperTransformations(graphics, flipper);

        // Draw body
     	graphics.fillRoundRect(
     	        0,
                0,
                flipper.getWidth() * Model.L_TO_PIXELS / 4,
                flipper.getHeight() * Model.L_TO_PIXELS,
                Model.L_TO_PIXELS / 2,
                Model.L_TO_PIXELS / 2);

        // Draw pivot
    	graphics.setColor(Color.RED);
    	graphics.fillOval(
    	        Model.L_TO_PIXELS/8,
                Model.L_TO_PIXELS/8,
                Model.L_TO_PIXELS/4,
                Model.L_TO_PIXELS/4);

        // Restore transformation
    	graphics.setTransform(originalTransform);
    }

	private static void performFlipperTransformations(Graphics2D graphics, ReadGizmo flipper) {
	    int middleX = flipper.getWidth() * Model.L_TO_PIXELS / 2;
        int middleY = flipper.getHeight() * Model.L_TO_PIXELS / 2;
		graphics.translate(middleX, middleY);

        // Perform rotation according to gizmo rotation
        graphics.rotate(flipper.getRotation().getRadiansFromNorth());
        // Flip horizontally if appropriate
    	if (flipper.getType() == GizmoType.RIGHT_FLIPPER) {
    		graphics.scale(-1, 1);
    	}
        graphics.translate(-middleX, -middleY);

        // Translate to pivot point
    	graphics.translate(0.25 * Model.L_TO_PIXELS, 0.25 * Model.L_TO_PIXELS);
    	try {
			graphics.rotate(-Math.toRadians(flipper.getPivotAngle()));
		} catch (GizmoTypeException e1) {
			assert false : "This Gizmo should have a pivot angle, since it's a flipper";
		}
		// Translate back after rotation
        graphics.translate(-0.25 * Model.L_TO_PIXELS, -0.25 * Model.L_TO_PIXELS);
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
