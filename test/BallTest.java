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
		assertEquals(firstBallPositionX, myBall.getX(), DELTA);
	}

	@Test
	public void getXMySecondBall() {
		assertEquals(secondBallPositionX, mySecondBall.getX(), DELTA);
	}

	@Test
	public void getYMyBall() {
		assertEquals(firstBallPositionY, myBall.getY(), DELTA);
	}

	@Test
	public void getYMySecondBall() {
		assertEquals(secondBallPositionY, mySecondBall.getY(), DELTA);
	}

	@Test
	public void getRadius() {
		assertEquals(0.25, myBall.getRadius(), DELTA);
	}

	@Test
	public void getPositionMyBall() {
		assertEquals(new Vect(firstBallPositionX, firstBallPositionY), myBall.getPosition());
	}

	@Test
	public void getPositionMySecondBall() {
		assertEquals(new Vect(secondBallPositionX, secondBallPositionY), mySecondBall.getPosition());
	}

	@Test
	public void setX() {
	    int newX = 1;

		myBall.setX(newX);
		assertEquals(newX, myBall.getX(), DELTA);
	}

	@Test
	public void setY() {
	    int newY = 1;

		myBall.setY(newY);
		assertEquals(newY, myBall.getY(), DELTA);
	}

	@Test
	public void setPosition() {
	    int newX = 2;
	    int newY = 3;

		myBall.setPosition(new Vect(newX, newY));
		assertEquals(new Vect(newX, newY), myBall.getPosition());
	}

	@Test
	public void getVelocityX() {
		assertEquals(secondBallVelocityX, mySecondBall.getVelocityX(), DELTA);
	}

	@Test
	public void getVelocityY() {
		assertEquals(secondBallVelocityY, mySecondBall.getVelocityY(), DELTA);
	}

	@Test
	public void getVelocity() {
		assertEquals(new Vect(secondBallVelocityX, secondBallVelocityY), mySecondBall.getVelocity());
	}

	@Test
	public void setVelocityX() {
	    int newVeloX = 4;

		myBall.setVelocityX(newVeloX);
		assertEquals(newVeloX, myBall.getVelocityX(), DELTA);
	}

	@Test
	public void setVelocityY() {
	    int newVeloY = 4;

		myBall.setVelocityY(newVeloY);
		assertEquals(newVeloY, myBall.getVelocityY(), DELTA);
	}

	@Test
	public void setVelocity() {
	    Vect newVelo = new Vect(4, 5);

		myBall.setVelocity(newVelo);
		assertEquals(newVelo, myBall.getVelocity());
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

		assertEquals(newCircle, myBall.getCircle());
	}

	@Test
	public void getCells() {
		assertEquals(4, mySecondBall.getCells().size());
	}
}
