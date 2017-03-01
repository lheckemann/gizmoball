package test;

import gizmoball.model.Ball;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.Vect;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BallTest {
	private Ball myBall;
	private Ball mySecondBall;

	private final double DELTA = 1e-15;

	@Before
	public void setUp() {
		myBall = new Ball();

		Ball newBall = new Ball();
		newBall.setPosition(new Vect(10, 12));
		newBall.setVelocity(new Vect(5, 6));
		mySecondBall = new Ball(newBall);
	}

	@Test
	public void getXMyBall() {
		assertEquals(myBall.getX(), 0, DELTA);
	}

	@Test
	public void getXMySecondBall() {
		assertEquals(mySecondBall.getX(), 10, DELTA);
	}

	@Test
	public void getYMyBall() {
		assertEquals(myBall.getY(), 0, DELTA);
	}

	@Test
	public void getYMySecondBall() {
		assertEquals(mySecondBall.getY(), 12, DELTA);
	}

	@Test
	public void getRadius() {
		assertEquals(myBall.getRadius(), 0.25, DELTA);
	}

	@Test
	public void getPositionMyBall() {
		assertEquals(myBall.getPosition(), new Vect(0, 0));
	}

	@Test
	public void getPositionMySecondBall() {
		assertEquals(mySecondBall.getPosition(), new Vect(10, 12));
	}

	@Test
	public void setX() {
		myBall.setX(1);
		assertEquals(myBall.getX(), 1, DELTA);
	}

	@Test
	public void setY() {
		myBall.setY(1);
		assertEquals(myBall.getY(), 1, DELTA);
	}

	@Test
	public void setPosition() {
		myBall.setPosition(new Vect(2, 3));
		assertEquals(myBall.getPosition(), new Vect(2, 3));
	}

	@Test
	public void getVelocityX() {
		assertEquals(mySecondBall.getVelocityX(), 5, DELTA);
	}

	@Test
	public void getVelocityY() {
		assertEquals(mySecondBall.getVelocityY(), 6, DELTA);
	}

	@Test
	public void getVelocity() {
		assertEquals(mySecondBall.getVelocity(), new Vect(5, 6));
	}

	@Test
	public void setVelocityX() {
		myBall.setVelocityX(4);
		assertEquals(myBall.getVelocityX(), 4, DELTA);
	}

	@Test
	public void setVelocityY() {
		myBall.setVelocityY(4);
		assertEquals(myBall.getVelocityY(), 4, DELTA);
	}

	@Test
	public void setVelocity() {
		myBall.setVelocity(new Vect(4, 5));
		assertEquals(myBall.getVelocity(), new Vect(4, 5));
	}

	@Test
	public void contains() {
		// TODO
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