package gizmoball.view.elements;

import gizmoball.model.Model;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class FlipperView {
	
    public static void paint(Graphics2D graphics, ReadGizmo flipper) {
    	graphics.setColor(Color.ORANGE);
    	
    	//Need to keep a copy of the original transform state, so that
    	//a right flipper transform doesn't affect future draws
    	AffineTransform originalTransform = graphics.getTransform();
    	
    	//We need to get the value of the middle of the flipper's bounding box to safely perform translations and rotations
    	int middleX = (flipper.getX() + (flipper.getX() + flipper.getWidth()))/2;
    	int middleY = (flipper.getY() + (flipper.getY() + flipper.getHeight()))/2;
    	
    	//Make sure that the translate call is done before the scale and rotate calls
    	graphics.translate(middleX * Model.L_TO_PIXELS, middleY * Model.L_TO_PIXELS);
    	graphics.scale(1, -1);
    	
    	switch(flipper.getRotation()) {
    		case EAST:
    			graphics.rotate(Math.toRadians(-90));
    			break;
    		case SOUTH:
    			graphics.rotate(Math.toRadians(-180));
    			break;
    		case WEST:
    			graphics.rotate(Math.toRadians(-270));
    			break;
    		default:
    			break;
    	}

    	//Make sure these two translate calls are done before translating back to 
    	//the original coordinates
    	graphics.scale(1, -1);
    	
    	if(flipper.getType() == GizmoType.RIGHT_FLIPPER) {
    	    graphics.scale(-1, 1);
    	}
    	
    	
    	graphics.translate(-middleX * Model.L_TO_PIXELS, -middleY * Model.L_TO_PIXELS);
    
    	//Divide flipper width by 4, since they should only take up a quarter of the box's width
    	graphics.fillRoundRect(flipper.getX() * Model.L_TO_PIXELS, flipper.getY() * Model.L_TO_PIXELS, flipper.getWidth() * Model.L_TO_PIXELS / 4 , flipper.getHeight() * Model.L_TO_PIXELS, Model.L_TO_PIXELS / 2, Model.L_TO_PIXELS / 2);
    	//graphics.fillRect(flipper.getX() * Model.L_TO_PIXELS, flipper.getY() * Model.L_TO_PIXELS, flipper.getWidth() * Model.L_TO_PIXELS, flipper.getHeight() * Model.L_TO_PIXELS);
    	
    	//Draw the pivot point so that we know what way the flipper pivots
    	graphics.setColor(Color.red);
    	graphics.fillOval((flipper.getX() + flipper.getWidth() / 8) * Model.L_TO_PIXELS , (flipper.getY() + flipper.getHeight() / 8) * Model.L_TO_PIXELS, Model.L_TO_PIXELS/8, Model.L_TO_PIXELS/8);
    	
    	graphics.setTransform(originalTransform);
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
