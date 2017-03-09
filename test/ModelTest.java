package test;

import gizmoball.model.Model;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.model.ReadBall;
import gizmoball.model.gizmos.Circle;
import gizmoball.model.gizmos.Gizmo;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.model.gizmos.Rotation;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ModelTest {
    private Model myModel;
    private Model emptyModel;

    private final double DELTA = 1e-15;

    @Before
    public void setUp() {
        myModel = new Model(20, 20);
        emptyModel = new Model(20, 20);

        try {
            myModel.select(1.5, 1);
            myModel.addBall(0.0, 0.0);
            myModel.select(1, 10);
            myModel.addTriangle();
            myModel.rotateGizmo();
            myModel.rotateGizmo();
            myModel.rotateGizmo();
            myModel.select(10, 13);
            myModel.addCircle();
            myModel.select(5, 18);
            myModel.addSquare();
            myModel.select(10, 18);
            myModel.addSquare();
            myModel.select(15, 18);
            myModel.addSquare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resetGizmoSetEmpty() {
        myModel.reset();

        assertEquals(myModel.getGizmos(), Collections.EMPTY_SET);
    }

    @Test
    public void resetBallSetEmpty() {
        myModel.reset();

        assertEquals(myModel.getBalls(), Collections.EMPTY_SET);
    }

    @Test
    public void resetKeyPressMapEmpty() {
        myModel.reset();

        assertEquals(myModel.getKeyPressToGizmoMap(), Collections.EMPTY_MAP);
    }

    @Test
    public void resetKeyReleaseMapEmpty() {
        myModel.reset();

        assertEquals(myModel.getKeyReleaseToGizmoMap(), Collections.EMPTY_MAP);
    }

    @Test
    public void resetGizmoToGizmoMapEmpty() {
        myModel.reset();

        assertEquals(myModel.getGizmoToGizmoMap(), Collections.EMPTY_MAP);
    }

    @Test
    public void select() {
        // not possible to test it directly
    }

    @Test
    public void moveAlreadyPresentToUnoccupiedSquare() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(19, 19);
        myModel.move(15, 18);

        for(ReadGizmo g :  myModel.getGizmos()) {
            if(g.getX() == 19 && g.getY() == 19)
                fail();
        }

        assertTrue(true);
    }

    @Test(expected=PositionOverlapException.class)
    public void moveAlreadyPresentToOccupiedSquare() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(5, 18);
        myModel.move(15, 18);

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void moveAlreadyPresentToOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(5, 18);
        myModel.move(20, 20);

        fail();
    }

    @Test
    public void moveNotPresent() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(6, 6);
        myModel.move(10, 10);

        for(ReadGizmo g :  myModel.getGizmos()) {
            if(g.getX() == 10 && g.getY() == 10)
                fail();
        }

        assertTrue(true);
    }

    @Test
    public void moveBall() throws PositionOverlapException, PositionOutOfBoundsException {
        /*myModel.select(1.5, 1);
        myModel.move(6, 6);

        for(ReadBall g :  myModel.getBalls()) {
            if(g.getX() > 5.9999 && g.getX() < 6.0001 && g.getY() > 5.9999 && g.getY() < 6.0001)
                assertTrue(true);
        }

        fail();*/
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void addAbsorberUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addAbsorber(5, 5);
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addAbsorberOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(14, 14);
        emptyModel.addCircle();

        emptyModel.select(10, 11);
        emptyModel.addAbsorber(5, 5);

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addAbsorberOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        emptyModel.addAbsorber(10, 10);

        fail();
    }

    @Test
    public void addSquareUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addSquare();
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addSquareOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addCircle();

        emptyModel.select(5, 5);
        emptyModel.addSquare();

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addSquareOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addSquare();

        fail();
    }

    @Test
    public void addCircleUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addCircle();
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addCircleOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addCircle();

        emptyModel.select(5, 5);
        emptyModel.addCircle();

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addCircleOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addCircle();

        fail();
    }

    @Test
    public void addTriangleUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addTriangle();
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addTriangleOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addCircle();

        emptyModel.select(5, 5);
        emptyModel.addTriangle();

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addTriangleOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addTriangle();

        fail();
    }

    @Test
    public void addRightFlipperUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addRightFlipper();
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addRightFlipperOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addCircle();

        emptyModel.select(5, 5);
        emptyModel.addRightFlipper();

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addRightFlipperOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addRightFlipper();

        fail();
    }

    @Test
    public void addLeftFlipperUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addLeftFlipper();
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addLeftFlipperOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addCircle();

        emptyModel.select(5, 5);
        emptyModel.addLeftFlipper();

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addLeftFlipperOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addLeftFlipper();

        fail();
    }


    // TODO
    @Test
    public void rotatePresentGizmo() {

    }

    @Test
    public void addBallUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addBall(0, 0);
        assertTrue(emptyModel.getBalls().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addBallOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addCircle();

        emptyModel.select(5, 5);
        emptyModel.addBall(0, 0);

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addBallOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addBall(0, 0);

        fail();
    }

    /*
    myModel.select(1.5, 1);
            myModel.addBall(0.0, 0.0);
     */
    @Test
    public void setBallVelocityExistingBall() {
        myModel.select(1.5, 1);
        myModel.setBallVelocity(4, 5);

        // TODO
    }

    @Test
    public void setBallVelocityNotExistingBall() {
        myModel.select(6, 6);
        myModel.setBallVelocity(4, 5);

        for(ReadBall b : myModel.getBalls())
        {
            if(b.getVelocityX() == 4 && b.getVelocityY() == 5)
                fail();
        }

        assertTrue(true);
    }

    @Test
    public void getGravity() {
        assertEquals(myModel.getGravity(), 25, DELTA);
    }

    @Test
    public void setGravity() {
        myModel.setGravity(5);
        assertEquals(myModel.getGravity(), 5, DELTA);
    }

    @Test
    public void getFrictionMu() {
        assertEquals(myModel.getFrictionMu(), 0.025, DELTA);
    }

    @Test
    public void getFrictionMu2() {
        assertEquals(myModel.getFrictionMu2(), 0.025, DELTA);
    }

    @Test
    public void setFriction() {
        myModel.setFriction(5, 6);

        // TODO
    }

    @Test
    public void triggerOnKeyPressExistingGizmo() {
        myModel.select(10, 13);
        myModel.triggerOnKeyPress(30);

        assertTrue(myModel.getKeyPressToGizmoMap().containsKey(30));
    }

    @Test
    public void triggerOnKeyPressNonExistingGizmo() {
        myModel.select(6, 6);
        myModel.triggerOnKeyPress(30);

        assertFalse(myModel.getKeyPressToGizmoMap().containsKey(30));
    }

    @Test
    public void triggerOnKeyReleaseExistingGizmo() {
        myModel.select(10, 13);
        myModel.triggerOnKeyRelease(30);

        assertTrue(myModel.getKeyReleaseToGizmoMap().containsKey(30));
    }

    @Test
    public void triggerOnKeyReleaseNonExistingGizmo() {
        myModel.select(6, 6);
        myModel.triggerOnKeyPress(30);

        assertFalse(myModel.getKeyReleaseToGizmoMap().containsKey(30));
    }

    @Test
    public void triggerOnOuterWalls() {
        // Can't test it
    }

    @Test
    public void triggerOnGizmoExistingDestinationAndSource() {
        ReadGizmo g = myModel.getGizmos().iterator().next();

        myModel.select(10, 13);
        myModel.triggerOnGizmo(g);

        assertTrue(myModel.getGizmoToGizmoMap().containsKey(g));
    }

    @Test
    public void triggerOnGizmoExistingDestinationAndNotExistingSource() {
        ReadGizmo g = myModel.getGizmos().iterator().next();

        myModel.select(6, 6);
        myModel.triggerOnGizmo(g);

        assertFalse(myModel.getGizmoToGizmoMap().containsKey(g));
    }

    @Test
    public void triggerOnGizmoNotExistingDestinationAndNotExistingSource() {
        ReadGizmo g = new Circle();

        myModel.select(6, 6);
        myModel.triggerOnGizmo(g);

        assertFalse(myModel.getGizmoToGizmoMap().containsKey(g));
    }

    @Test
    public void triggerOnGizmoNotExistingDestinationAndExistingSource() {
        ReadGizmo g = new Circle();

        myModel.select(10, 13);
        myModel.triggerOnGizmo(g);

        assertFalse(myModel.getGizmoToGizmoMap().containsKey(g));
    }

    @Test
    public void keyPressed() {

    }

    @Test
    public void keyReleased() {

    }

    @Test
    public void gizmoHit() {

    }

    @Test
    public void wallHit() {

    }

    @Test
    public void getGizmos() {

    }

    @Test
    public void getBalls() {

    }

    @Test
    public void getWidth() {
        assertEquals(myModel.getWidth(), 20);
    }

    @Test
    public void getHeight() {
        assertEquals(myModel.getWidth(), 20);
    }

    @Test
    public void getKeyPressToGizmoMap() {

    }

    @Test
    public void getKeyReleaseToGizmoMap() {

    }

    @Test
    public void getGizmoToGizmoMap() {

    }

    @Test
    public void load() {

    }

    @Test
    public void tick() {

    }

    @Test
    public void save() {

    }
}