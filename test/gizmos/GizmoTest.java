package test.gizmos;

import gizmoball.model.gizmos.Flipper;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.Rotation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    // setRotation is not being used at all. Need to double check
    /*@Test
    public void setRotation() {

    }*/

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
    public void getTransform() {
        // TODO
    }

    @Test
    public void getCells() {
        // TODO
    }

    @Test
    public void equals() {
        // TODO
    }
}