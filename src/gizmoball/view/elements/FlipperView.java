package gizmoball.view.elements;

import gizmoball.model.Model;
import gizmoball.model.gizmos.GizmoTypeException;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class FlipperView {
	
	
	public static void paint(Graphics2D graphics, ReadGizmo flipper) {
    	graphics.setColor(Color.ORANGE);
    	AffineTransform originalTransform = graphics.getTransform();
    	
    	performFlipperTransformations(graphics, flipper);
    		
     	graphics.fillRoundRect(flipper.getX() * Model.L_TO_PIXELS, flipper.getY() * Model.L_TO_PIXELS, flipper.getWidth() * Model.L_TO_PIXELS / 4 , flipper.getHeight() * Model.L_TO_PIXELS, Model.L_TO_PIXELS / 2, Model.L_TO_PIXELS / 2);
    	graphics.setColor(Color.RED);
    	graphics.translate(0.15 * Model.L_TO_PIXELS, 0.15 * Model.L_TO_PIXELS);
    	graphics.fillOval(flipper.getX() * Model.L_TO_PIXELS, flipper.getY() * Model.L_TO_PIXELS, Model.L_TO_PIXELS/4, Model.L_TO_PIXELS/4);
    	
    	graphics.setTransform(originalTransform);
    }
	
	private static void performFlipperTransformations(Graphics2D graphics, ReadGizmo flipper) {
	
    	int topRightHandX = flipper.getX() + flipper.getWidth();
    	int bottomRightHandY = flipper.getY() + flipper.getHeight();
    	int middleX = (flipper.getX() + topRightHandX)/2;
    	int middleY = (flipper.getY() + bottomRightHandY)/2;
		
		graphics.translate(middleX * Model.L_TO_PIXELS, middleY * Model.L_TO_PIXELS);
    	
    	if (flipper.getType() == GizmoType.RIGHT_FLIPPER) {
    		graphics.scale(-1, 1);
    	}
    	switch (flipper.getRotation()) {
    		case EAST:
    			graphics.rotate(Math.toRadians(90));
    			break;
    		case SOUTH:
    			graphics.rotate(Math.toRadians(180));
    			break;
    		case WEST:
    			graphics.rotate(Math.toRadians(270));
    			break;
    		default:
    			break;
    	}
    	graphics.translate(-0.75 * Model.L_TO_PIXELS, -0.75 * Model.L_TO_PIXELS);
    	try {
			graphics.rotate(-Math.toRadians(flipper.getPivotAngle()));
		} catch (GizmoTypeException e1) {
			assert false : "This Gizmo should have a pivot angle, since it's a flipper";
		}
    	graphics.translate(0.75 * Model.L_TO_PIXELS, 0.75 * Model.L_TO_PIXELS);
    	graphics.translate(-middleX * Model.L_TO_PIXELS, -middleY * Model.L_TO_PIXELS);
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
