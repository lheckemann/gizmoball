package test;

import gizmoball.model.Ball;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.Vect;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BallTest {
	private Ball myBall;
	private Ball mySecondBall;

	private final double DELTA = 1e-15;

	private final double firstBallPositionX = 0;
    private final double firstBallPositionY = 0;

    private final double secondBallPositionX = 10;
    private final double secondBallPositionY = 12;
	private final double secondBallVelocityX = 5;
	private final double secondBallVelocityY = 6;

	@Before
	public void setUp() {
		myBall = new Ball();

		Ball newBall = new Ball();
		newBall.setPosition(new Vect(secondBallPositionX, secondBallPositionY));
		newBall.setVelocity(new Vect(secondBallVelocityX, secondBallVelocityY));
		mySecondBall = new Ball(newBall);
	}

	@Test
	public void getXMyBall() {
		assertEquals(myBall.getX(), firstBallPositionX, DELTA);
	}

	@Test
	public void getXMySecondBall() {
		assertEquals(mySecondBall.getX(), secondBallPositionX, DELTA);
	}

	@Test
	public void getYMyBall() {
		assertEquals(myBall.getY(), firstBallPositionY, DELTA);
	}

	@Test
	public void getYMySecondBall() {
		assertEquals(mySecondBall.getY(), secondBallPositionY, DELTA);
	}

	@Test
	public void getRadius() {
		assertEquals(myBall.getRadius(), 0.25, DELTA);
	}

	@Test
	public void getPositionMyBall() {
		assertEquals(myBall.getPosition(), new Vect(firstBallPositionX, firstBallPositionY));
	}

	@Test
	public void getPositionMySecondBall() {
		assertEquals(mySecondBall.getPosition(), new Vect(secondBallPositionX, secondBallPositionY));
	}

	@Test
	public void setX() {
	    int newX = 1;

		myBall.setX(newX);
		assertEquals(myBall.getX(), newX, DELTA);
	}

	@Test
	public void setY() {
	    int newY = 1;

		myBall.setY(newY);
		assertEquals(myBall.getY(), newY, DELTA);
	}

	@Test
	public void setPosition() {
	    int newX = 2;
	    int newY = 3;

		myBall.setPosition(new Vect(newX, newY));
		assertEquals(myBall.getPosition(), new Vect(newX, newY));
	}

	@Test
	public void getVelocityX() {
		assertEquals(mySecondBall.getVelocityX(), secondBallVelocityX, DELTA);
	}

	@Test
	public void getVelocityY() {
		assertEquals(mySecondBall.getVelocityY(), secondBallVelocityY, DELTA);
	}

	@Test
	public void getVelocity() {
		assertEquals(mySecondBall.getVelocity(), new Vect(secondBallVelocityX, secondBallVelocityY));
	}

	@Test
	public void setVelocityX() {
	    int newVeloX = 4;

		myBall.setVelocityX(newVeloX);
		assertEquals(myBall.getVelocityX(), newVeloX, DELTA);
	}

	@Test
	public void setVelocityY() {
	    int newVeloY = 4;

		myBall.setVelocityY(newVeloY);
		assertEquals(myBall.getVelocityY(), newVeloY, DELTA);
	}

	@Test
	public void setVelocity() {
	    Vect newVelo = new Vect(4, 5);

		myBall.setVelocity(newVelo);
		assertEquals(myBall.getVelocity(), newVelo);
	}

	@Test
	public void containsCenterCoordinates() {
		assertTrue(mySecondBall.contains(secondBallPositionX, secondBallPositionY));
	}

	@Test
	public void containsBorderCoordinates() {
		assertTrue(mySecondBall.contains(secondBallPositionX + 0.24, secondBallPositionY));
	}

	@Test
	public void notContainsBitMoreThanBorderCoordinates() {
		assertFalse(mySecondBall.contains(secondBallPositionX + 0.25, secondBallPositionY + 0.25));
	}

	@Test
	public void notContainsGreaterCoordinates() {
		assertFalse(mySecondBall.contains(secondBallPositionX + 0.26, secondBallPositionY + 0.26));
	}

	@Test
	public void getCircle() {
		int x = 4;
		int y = 5;

		myBall.setPosition(new Vect(x, y));
		Circle newCircle = new Circle(x, y, 0.25);

		assertEquals(myBall.getCircle(), newCircle);
	}

	@Test
	public void getCells() {
		Set<Vect> cells = Collections.singleton(new Vect((int) mySecondBall.getX(), (int) mySecondBall.getY()));

		assertEquals(mySecondBall.getCells(), cells);
	}
}