

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

import gizmoball.model.*;
import gizmoball.model.gizmos.*;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

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
            myModel.addBall(new Ball());
            myModel.select(1, 10);
            myModel.addGizmo(new Triangle());
            myModel.rotateGizmo();
            myModel.rotateGizmo();
            myModel.rotateGizmo();
            myModel.select(10, 13);
            myModel.addGizmo(new Circle());
            myModel.select(5, 18);
            myModel.addGizmo(new Square());
            myModel.select(10, 18);
            myModel.addGizmo(new Square());
            myModel.select(15, 18);
            myModel.addGizmo(new Square());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resetGizmoSetEmpty() {
        myModel.reset();

        assertEquals(Collections.EMPTY_SET, myModel.getGizmos());
    }

    @Test
    public void resetBallSetEmpty() {
        myModel.reset();

        assertEquals(Collections.EMPTY_SET, myModel.getBalls());
    }

    @Test
    public void resetKeyPressMapEmpty() {
        myModel.reset();

        assertEquals(Collections.EMPTY_MAP, myModel.getKeyPressToGizmoMap());
    }

    @Test
    public void resetKeyReleaseMapEmpty() {
        myModel.reset();

        assertEquals(Collections.EMPTY_MAP, myModel.getKeyReleaseToGizmoMap());
    }

    @Test
    public void resetGizmoToGizmoMapEmpty() {
        myModel.reset();

        assertEquals(Collections.EMPTY_MAP, myModel.getGizmoToGizmoMap());
    }

    @Test
    public void moveAlreadyPresentGizmoToUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        boolean result = false;
        myModel.select(15, 18);
        myModel.move(19, 19);

        for(ReadGizmo g :  myModel.getGizmos()) {
            if(g.getX() == 19 && g.getY() == 19) {
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test(expected=PositionOverlapException.class)
    public void moveAlreadyPresentGizmoToOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(5, 18);
        myModel.move(15, 18);

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void moveAlreadyPresentGizmoToOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(5, 18);
        myModel.move(20, 20);

        fail();
    }

    @Test
    public void moveNotPresent() throws PositionOverlapException, PositionOutOfBoundsException {
        boolean result = true;
        myModel.select(6, 6);
        myModel.move(10, 10);

        for(ReadGizmo g :  myModel.getGizmos()) {
            if(g.getX() == 10 && g.getY() == 10) {
                result = false;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void moveBallToUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        boolean result = false;
        myModel.select(1.5, 1);
        myModel.move(6, 6);

        for(ReadBall g :  myModel.getBalls()) {
            if((g.getX() - 6.0) <= 0.01 && (g.getY() - 6.0) <= 0.01) {
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test(expected=PositionOverlapException.class)
    public void moveBallToOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        myModel.select(1.5, 1);
        myModel.move(15, 18);

        fail();
    }

    @Test
    public void deleteNotPresent() {
        int size = myModel.getGizmos().size();
        myModel.select(6, 6);
        myModel.delete();

        assertEquals(size, myModel.getGizmos().size());
    }

    @Test
    public void deleteExistingBall() {
        int size = myModel.getBalls().size();
        myModel.select(1.5, 1);
        myModel.delete();

        assertEquals(size - 1, myModel.getBalls().size());
    }

    @Test
    public void deleteExistingGizmoCheckNumberOfGizmosLeft() {
        int size = myModel.getGizmos().size();
        myModel.select(10, 13);
        myModel.delete();

        assertEquals(size - 1, myModel.getGizmos().size());
    }
    
    @Test
    public void deleteConnectionFromAllMaps() {
        Gizmo source = new Triangle();
        Gizmo dest = new Square();
        try {
            myModel.select(7, 7);
            myModel.addGizmo(source);
            myModel.select(6, 6);
            myModel.addGizmo(dest);
            myModel.keyPressed(10);
            myModel.keyReleased(15);
            myModel.triggerOnGizmo(source);
            myModel.delete();
        } catch (PositionOverlapException e) {
            e.printStackTrace();
        } catch (PositionOutOfBoundsException e) {
            e.printStackTrace();
        }

        assertTrue(!myModel.getKeyPressToGizmoMap().containsKey(dest) &&
                !myModel.getKeyReleaseToGizmoMap().containsKey(dest) &&
                !myModel.getGizmoToGizmoMap().containsKey(dest));
    }

    @Test
    public void addAbsorberUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        try {
            emptyModel.addGizmo(new Absorber(5, 5));
            assertTrue(emptyModel.getGizmos().size() > 0);
        } catch (InvalidAbsorberWidthHeight e) {
            fail();
        }
    }

    @Test(expected=PositionOverlapException.class)
    public void addAbsorberOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(14, 14);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(10, 11);
        try {
            emptyModel.addGizmo(new Absorber(5, 5));
        } catch (InvalidAbsorberWidthHeight e) { }

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addAbsorberOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        try {
            emptyModel.addGizmo(new Absorber(10, 10));
        } catch (InvalidAbsorberWidthHeight e) { }

        fail();
    }

    @Test
    public void addSquareUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);

        emptyModel.addGizmo(new Square());
        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addSquareOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Square());

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addSquareOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addGizmo(new Square());

        fail();
    }

    @Test
    public void addCircleUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        emptyModel.addGizmo(new Circle());

        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addCircleOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addCircleOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addGizmo(new Circle());

        fail();
    }

    @Test
    public void addTriangleUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        emptyModel.addGizmo(new Triangle());

        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addTriangleOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Triangle());

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addTriangleOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addGizmo(new Triangle());

        fail();
    }

    @Test
    public void addRightFlipperUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        emptyModel.addGizmo(new StandardFlipper(false));

        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addRightFlipperOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(5, 5);
        emptyModel.addGizmo(new StandardFlipper(false));

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addRightFlipperOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addGizmo(new StandardFlipper(false));

        fail();
    }

    @Test
    public void addLeftFlipperUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        emptyModel.addGizmo(new StandardFlipper(true));

        assertTrue(emptyModel.getGizmos().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addLeftFlipperOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(5, 5);
        emptyModel.addGizmo(new StandardFlipper(true));

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addLeftFlipperOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addGizmo(new StandardFlipper(true));

        fail();
    }

    @Test(expected=NonRotatableException.class)
    public void rotateBall() throws NonRotatableException {
        try {
            emptyModel.select(4, 4);
            emptyModel.addBall(new Ball());
            emptyModel.rotateGizmo();
            fail();
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
            fail();
        }
    }
    
    @Test
    public void rotateNonPresent() {
        Set<ReadGizmo> gizmos = myModel.getGizmos();
        myModel.select(6, 6);
        try {
            myModel.rotateGizmo();
            assertEquals(gizmos, myModel.getGizmos());
        } catch (NonRotatableException e) {
            fail();
        }
    }

    @Test
    public void rotatePresentGizmo() {
        Set<ReadGizmo> gizmos = myModel.getGizmos();
        myModel.select(1, 10);

        ReadGizmo selectedGizmo = null;
        Rotation before = null;
        for(ReadGizmo g : gizmos) {
            if(g.getX() == 1 && g.getY() == 10) {
                selectedGizmo = g;
                before = g.getRotation();
            }
        }

        try {
            myModel.rotateGizmo();
            assertEquals(before.nextCW(), selectedGizmo.getRotation());
        } catch (NonRotatableException e) {
            fail();
        }
    }

    @Test
    public void addBallUnoccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(10, 11);
        emptyModel.addBall(new Ball());

        assertTrue(emptyModel.getBalls().size() > 0);
    }

    @Test(expected=PositionOverlapException.class)
    public void addBallOccupiedPosition() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(5, 5);
        emptyModel.addGizmo(new Circle());

        emptyModel.select(5, 5);
        emptyModel.addBall(new Ball());

        fail();
    }

    @Test(expected=PositionOutOfBoundsException.class)
    public void addBallOutOfBounds() throws PositionOverlapException, PositionOutOfBoundsException {
        emptyModel.select(20, 20);
        emptyModel.addBall(new Ball());

        fail();
    }

    @Test
    public void getGravity() {
        assertEquals(25, myModel.getGravity(), DELTA);
    }

    @Test
    public void setGravity() {
        myModel.setGravity(5);
        assertEquals(5, myModel.getGravity(), DELTA);
    }

    @Test
    public void getFrictionMu() {
        assertEquals(0.025, myModel.getFrictionMu(), DELTA);
    }

    @Test
    public void getFrictionMu2() {
        assertEquals(0.025, myModel.getFrictionMu2(), DELTA);
    }

    @Test
    public void setFriction() {
        myModel.setFriction(5, 6);

        assertTrue(myModel.getFrictionMu() == 5 && myModel.getFrictionMu2() == 6);
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
        myModel.triggerOnKeyRelease(30);

        assertFalse(myModel.getKeyReleaseToGizmoMap().containsKey(30));
    }

    @Test
    public void triggerOnOuterWallsNotPresentGizmo() {
    	myModel.select(6, 6);
        myModel.triggerOnOuterWalls();

        assertEquals(Collections.EMPTY_SET, myModel.getOuterwallTriggeredGizmos());
    }

    @Test
    public void triggerOnOuterWallsExistingGizmo() {
    	Gizmo t = new Triangle();
    	
        try {
        	myModel.select(6,  6);
			myModel.addGizmo(t);
			myModel.triggerOnOuterWalls();
		} catch (PositionOverlapException | PositionOutOfBoundsException e) {
			fail();
		};

		Set<ReadGizmo> outerWalls = new HashSet<ReadGizmo>();
		outerWalls.add(t);
        assertEquals(outerWalls, myModel.getOuterwallTriggeredGizmos());
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
    public void getGizmos() {
        Model model = new Model(20, 20);
        Gizmo triangle = new Triangle();
        Gizmo square = new Square();
        Gizmo sink = new Sink();

        try {
            model.select(1, 1);
            model.addGizmo(triangle);
            model.select(2, 2);
            model.addGizmo(square);
            model.select(3, 3);
            model.addGizmo(sink);
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
            fail();
        }

        List<ReadGizmo> gizmos = new ArrayList<ReadGizmo>();
        gizmos.add(triangle);
        gizmos.add(square);
        gizmos.add(sink);

        assertEquals(new HashSet<>(gizmos), model.getGizmos());
    }

    @Test
    public void getGizmosEmptySet() {
        Model model = new Model(20, 20);

        assertEquals(Collections.EMPTY_SET, model.getGizmos());
    }

    @Test
    public void getBalls() {
        Model model = new Model(20, 20);
        Ball ball1 = new Ball();
        Ball ball2 = new Ball();

        ball1.setPosition(new Vect(1, 1));
        ball1.setVelocity(new Vect(4, 4));

        ball2.setPosition(new Vect(3, 3));
        ball2.setVelocity(new Vect(5, 5));

        try {
            model.select(1, 1);
            model.addBall(ball1);
            model.select(3, 3);
            model.addBall(ball2);
        } catch (PositionOverlapException | PositionOutOfBoundsException e) {
            fail();
        }

        List<ReadBall> balls = new ArrayList<ReadBall>();
        balls.add(ball1);
        balls.add(ball2);

        assertEquals(new HashSet<>(balls), model.getBalls());
    }

    @Test
    public void getBallsEmptySet() {
        Model model = new Model(20, 20);

        assertEquals(Collections.EMPTY_SET, model.getBalls());
    }

    @Test
    public void getWidth() {
        assertEquals(20, myModel.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(20, myModel.getHeight());
    }

    @Test
    public void testRotateAfterMoveDoesntDeletesGizmo() {

        Model model = new Model(20, 20);
        int startModelSize = 0;
        try {
            model.select(5, 5);
            model.addGizmo(new Triangle());
            model.select(6, 6);
            model.addGizmo(new Circle());

            startModelSize = model.getGizmos().size();
            model.select(5, 5);
            model.move(6, 6); //Intended position overlap exception is thrown here
        } catch (PositionOverlapException e) {
            model.select(6, 6);
            try {
                model.rotateGizmo();
                if (model.getGizmos().size() != startModelSize) {
                    fail();
                } else {
                    assertTrue(true);
                }
            } catch (NonRotatableException e1) { }
        } catch(PositionOutOfBoundsException e) { }
    }

    @Test
    public void getKeyPressToGizmoMapDifferentGizmoDifferentKeys() {
        myModel.select(1, 10);
        myModel.triggerOnKeyPress(10);
        myModel.select(10, 13);
        myModel.triggerOnKeyPress(11);

        assertTrue(myModel.getKeyPressToGizmoMap().containsKey(10) && myModel.getKeyPressToGizmoMap().containsKey(11));
    }

    @Test
    public void getKeyPressToGizmoMapDifferentGizmoSameKeys() {
        Gizmo triangle = new Triangle();
        Gizmo square = new Square();
        try {
            myModel.select(6, 6);
            myModel.addGizmo(triangle);
            myModel.triggerOnKeyPress(10);
            myModel.select(7, 7);
            myModel.addGizmo(square);
            myModel.triggerOnKeyPress(10);
        } catch (PositionOverlapException e) {
            e.printStackTrace();
        } catch (PositionOutOfBoundsException e) {
            e.printStackTrace();
        }

        Set<ReadGizmo> gizmos = myModel.getKeyPressToGizmoMap().get(10);
        assertTrue(gizmos.contains(triangle) && gizmos.contains(square));
    }

    @Test
    public void getKeyPressToGizmoMapSameGizmoSameKeys() {
        myModel.select(1, 10);
        myModel.triggerOnKeyPress(10);
        myModel.triggerOnKeyPress(10);

        assertTrue(myModel.getKeyPressToGizmoMap().containsKey(10) &&
                myModel.getKeyPressToGizmoMap().size() == 1);
    }

    @Test
    public void getKeyPressToGizmoMapSameGizmoDifferentKeys() {
        myModel.select(1, 10);
        myModel.triggerOnKeyPress(10);
        myModel.triggerOnKeyPress(11);

        assertTrue(myModel.getKeyPressToGizmoMap().containsKey(10) &&
                myModel.getKeyPressToGizmoMap().containsKey(11) &&
                myModel.getKeyPressToGizmoMap().size() == 2);
    }

    @Test
    public void getKeyReleaseToGizmoMapDifferentGizmoDifferentKeys() {
        myModel.select(1, 10);
        myModel.triggerOnKeyRelease(10);
        myModel.select(10, 13);
        myModel.triggerOnKeyRelease(11);

        assertTrue(myModel.getKeyReleaseToGizmoMap().containsKey(10) && myModel.getKeyReleaseToGizmoMap().containsKey(11));
    }

    @Test
    public void getKeyReleaseToGizmoMapDifferentGizmoSameKeys() {
        Gizmo triangle = new Triangle();
        Gizmo square = new Square();
        try {
            myModel.select(6, 6);
            myModel.addGizmo(triangle);
            myModel.triggerOnKeyRelease(10);
            myModel.select(7, 7);
            myModel.addGizmo(square);
            myModel.triggerOnKeyRelease(10);
        } catch (PositionOverlapException e) {
            e.printStackTrace();
        } catch (PositionOutOfBoundsException e) {
            e.printStackTrace();
        }

        Set<ReadGizmo> gizmos = myModel.getKeyReleaseToGizmoMap().get(10);
        assertTrue(gizmos.contains(triangle) && gizmos.contains(square));
    }

    @Test
    public void getKeyReleaseToGizmoMapSameGizmoSameKeys() {
        myModel.select(1, 10);
        myModel.triggerOnKeyRelease(10);
        myModel.triggerOnKeyRelease(10);

        assertTrue(myModel.getKeyReleaseToGizmoMap().containsKey(10) &&
                myModel.getKeyReleaseToGizmoMap().size() == 1);
    }

    @Test
    public void getKeyReleaseToGizmoMapSameGizmoDifferentKeys() {
        myModel.select(1, 10);
        myModel.triggerOnKeyRelease(10);
        myModel.triggerOnKeyRelease(11);

        assertTrue(myModel.getKeyReleaseToGizmoMap().containsKey(10) &&
                myModel.getKeyReleaseToGizmoMap().containsKey(11) &&
                myModel.getKeyReleaseToGizmoMap().size() == 2);
    }

    @Test
    public void getGizmoToGizmoMapDifferentGizmo() {
        Gizmo source = new Triangle();
        Gizmo dest = new Square();
        try {
            myModel.select(7, 7);
            myModel.addGizmo(source);
            myModel.select(6, 6);
            myModel.addGizmo(dest);
            myModel.triggerOnGizmo(source);
        } catch (PositionOverlapException e) {
            e.printStackTrace();
        } catch (PositionOutOfBoundsException e) {
            e.printStackTrace();
        }

        assertTrue(myModel.getGizmoToGizmoMap().containsKey(source) &&
                myModel.getGizmoToGizmoMap().get(source).contains(dest));
    }

    @Test
    public void getGizmoToGizmoMapSameGizmo() {
        Gizmo g = new Triangle();
        try {
            myModel.select(7, 7);
            myModel.addGizmo(g);
            myModel.triggerOnGizmo(g);
        } catch (PositionOverlapException e) {
            e.printStackTrace();
        } catch (PositionOutOfBoundsException e) {
            e.printStackTrace();
        }

        assertTrue(myModel.getGizmoToGizmoMap().containsKey(g) &&
                myModel.getGizmoToGizmoMap().get(g).contains(g));
    }
}
