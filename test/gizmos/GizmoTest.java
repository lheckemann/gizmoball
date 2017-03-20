package test.gizmos;

import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import physics.Vect;

import gizmoball.model.gizmos.*;

public class GizmoTest {
    private Gizmo myLeftFlipper;
    private Gizmo myRightFlipper;

    @Before
    public void setUp() {
        myLeftFlipper = new StandardFlipper(true);
        myRightFlipper = new StandardFlipper(false);
    }


    @Test
    public void getRotationAtBeginning() {
        Assert.assertEquals( Rotation.NORTH, myLeftFlipper.getRotation());
    }

    @Test
    public void rotateOneTime() {
        try {
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        assertEquals(Rotation.EAST, myLeftFlipper.getRotation());
    }

    @Test
    public void rotateTwoTimes() {
        try {
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        assertEquals(Rotation.SOUTH, myLeftFlipper.getRotation());
    }

    @Test
    public void rotateThreeTimes() {
        try {
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        assertEquals(Rotation.WEST, myLeftFlipper.getRotation());
    }

    @Test
    public void rotateFourTimes() {
        try {
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        assertEquals(Rotation.NORTH, myLeftFlipper.getRotation());
    }

    @Test
    public void getXAtBeginning() {
        assertEquals(0, myLeftFlipper.getX());
    }

    @Test
    public void getYAtBeginning() {
        assertEquals(0, myLeftFlipper.getY());
    }

    @Test
    public void setXAndCheckNewX() {
        myLeftFlipper.setX(5);
        assertEquals(5, myLeftFlipper.getX());
    }

    @Test
    public void setYAndCheckNewY() {
        myLeftFlipper.setY(5);
        assertEquals(5, myLeftFlipper.getY());
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
        assertEquals(at, myLeftFlipper.getTransform());
    }
    
    @Test
    public void getTransformOneRotationLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        try {
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        AffineTransform at = new AffineTransform(0, 1, -1, 0, 6, 6);
        assertEquals(at, myLeftFlipper.getTransform());
    }
    
    @Test
    public void getTransformTwoRotationsLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        try {
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        AffineTransform at = new AffineTransform(-1, 0, 0, -1, 6, 8);
        assertEquals(at, myLeftFlipper.getTransform());
    }
    
    @Test
    public void getTransformThreeRotationsLeftFlipper() {
    	myLeftFlipper.setX(4);
        myLeftFlipper.setY(6);
        try {
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
            myLeftFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        AffineTransform at = new AffineTransform(0, -1, 1, 0, 4, 8);
        assertEquals(at, myLeftFlipper.getTransform());
    }
    
    @Test
    public void getTransformNoRotationRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
        AffineTransform at = new AffineTransform(-1, 0, 0, 1, 6, 6);
        assertEquals(at, myRightFlipper.getTransform());
    }
    
    @Test
    public void getTransformOneRotationRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
        try {
            myRightFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        AffineTransform at = new AffineTransform(0, -1, -1, 0, 6, 8);
        assertEquals(at, myRightFlipper.getTransform());
    }
    
    @Test
    public void getTransformTwoRotationsRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
    	try {
            myRightFlipper.rotate();
            myRightFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        AffineTransform at = new AffineTransform(1, 0, 0, -1, 4, 8);
        assertEquals(at, myRightFlipper.getTransform());
    }
    
    @Test
    public void getTransformThreeRotationsRightFlipper() {
    	myRightFlipper.setX(4);
    	myRightFlipper.setY(6);
    	try {
            myRightFlipper.rotate();
            myRightFlipper.rotate();
            myRightFlipper.rotate();
        } catch (NonRotatableException e) {
            fail();
        }
        AffineTransform at = new AffineTransform(0, 1, 1, 0, 4, 6);
        assertEquals(at, myRightFlipper.getTransform());
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
    	
    	assertEquals(cells, myLeftFlipper.getCells());
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
        assertFalse(myLeftFlipper.equals(circle));
    }
}
