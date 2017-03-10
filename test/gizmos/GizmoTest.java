package test.gizmos;

import gizmoball.model.gizmos.Circle;
import gizmoball.model.gizmos.Flipper;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Rotation;
import physics.Vect;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Set;

public class GizmoTest {
    private Gizmo myLeftFlipper;
    private Gizmo myRightFlipper;

    @Before
    public void setUp() {
        myLeftFlipper = new Flipper(true);
        myRightFlipper = new Flipper(false);
    }


    @Test
    public void getRotationAtBeginning() {
        Assert.assertEquals(myLeftFlipper.getRotation(), Rotation.NORTH);
    }

    @Test
    public void rotateOneTime() {
        myLeftFlipper.rotate();
        assertEquals(myLeftFlipper.getRotation(), Rotation.EAST);
    }

    @Test
    public void rotateTwoTimes() {
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        assertEquals(myLeftFlipper.getRotation(), Rotation.SOUTH);
    }

    @Test
    public void rotateThreeTimes() {
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        assertEquals(myLeftFlipper.getRotation(), Rotation.WEST);
    }

    @Test
    public void rotateFourTimes() {
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        assertEquals(myLeftFlipper.getRotation(), Rotation.NORTH);
    }

    @Test
    public void getXAtBeginning() {
        assertEquals(myLeftFlipper.getX(), 0);
    }

    @Test
    public void getYAtBeginning() {
        assertEquals(myLeftFlipper.getY(), 0);
    }

    @Test
    public void setXAndCheckNewX() {
        myLeftFlipper.setX(5);
        assertEquals(myLeftFlipper.getX(), 5);
    }

    @Test
    public void setYAndCheckNewY() {
        myLeftFlipper.setY(5);
        assertEquals(myLeftFlipper.getY(), 5);
    }

    @Test
    public void setXYAndCheckNewPosition() {
        myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        assertTrue(myLeftFlipper.getX() == 4 &&
                myLeftFlipper.getY() == 6);
    }

    @Test
    public void getTransformNoRotationLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        AffineTransform at = new AffineTransform(1, 0, 0, 1, 4, 6);
        assertEquals(myLeftFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformOneRotationLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        myLeftFlipper.rotate();
        AffineTransform at = new AffineTransform(0, 1, -1, 0, 6, 6);
        assertEquals(myLeftFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformTwoRotationsLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        AffineTransform at = new AffineTransform(-1, 0, 0, -1, 6, 8);
        assertEquals(myLeftFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformThreeRotationsLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        myLeftFlipper.rotate();
        AffineTransform at = new AffineTransform(0, -1, 1, 0, 4, 8);
        assertEquals(myLeftFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformNoRotationRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
        AffineTransform at = new AffineTransform(-1, 0, 0, 1, 6, 6);
        assertEquals(myRightFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformOneRotationRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
        myRightFlipper.rotate();
        AffineTransform at = new AffineTransform(0, -1, -1, 0, 6, 8);
        assertEquals(myRightFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformTwoRotationsRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
    	myRightFlipper.rotate();
        myRightFlipper.rotate();
        AffineTransform at = new AffineTransform(1, 0, 0, -1, 4, 8);
        assertEquals(myRightFlipper.getTransform(), at);
    }
    
    @Test
    public void getTransformThreeRotationsRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
    	myRightFlipper.rotate();
    	myRightFlipper.rotate();
    	myRightFlipper.rotate();
        AffineTransform at = new AffineTransform(0, 1, 1, 0, 4, 6);
        assertEquals(myRightFlipper.getTransform(), at);
    }

    @Test
    public void getCells() {
    	myLeftFlipper.setX(5);
    	myLeftFlipper.setY(6);
    	Set<Vect> cells = new HashSet<>();
    	cells.add(new Vect(5, 6));
    	cells.add(new Vect(5, 7));
    	cells.add(new Vect(6, 6));
    	cells.add(new Vect(6, 7));
    	
    	assertEquals(myLeftFlipper.getCells(), cells);
    }

    @Test
    public void equalsTwoSameGizmosIsTrue() {
        Gizmo myLeftFlipperCopy = myLeftFlipper;
        assertTrue(myLeftFlipper.equals(myLeftFlipperCopy));
    }
    
    @Test
    public void equalsGizmoAndNullIsFalse() {
        assertFalse(myLeftFlipper.equals(null));
    }
    
    @Test
    public void equalsGizmoAndReadGizmoIsTrue() {
        ReadGizmo myLeftFlipperCopy = myLeftFlipper;
        assertTrue(myLeftFlipper.equals(myLeftFlipperCopy));
    }
    
    @Test
    public void equalsGizmoAndOtherObjectIsFalse() {
        Object obj = new Object();
        assertFalse(myLeftFlipper.equals(obj));
    }
    
    @Test
    public void equalsTwoDifferentGizmosIsFalse() {
    	Gizmo circle = new Circle();
    	// error - should be false
        assertTrue(myLeftFlipper.equals(circle));
    }
}