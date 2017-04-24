/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
package gizmoball.model.gizmos;

import physics.*;
import physics.Circle;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gizmoball.model.Ball;
import gizmoball.model.ReadBall;

public class Absorber extends BaseGizmo {
    private final int width;
    private final int height;
    private Set<Ball> balls;

    public Absorber(int width, int height) throws InvalidAbsorberWidthHeight {
        if (width < 1 || height < 1) {
            throw new InvalidAbsorberWidthHeight("Absorber width or height is smaller than 1.");
        }
        this.width = width;
        this.height = height;
        this.balls = new HashSet<Ball>();
    }

    @Override
    public GizmoType getType() {
        return GizmoType.ABSORBER;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Set<LineSegment> getLineSegments() {
        return Collections.unmodifiableSet(
            Stream.of(
                    new LineSegment(0, 0, 0, height),
                    new LineSegment(0, height, width, height),
                    new LineSegment(width, height, width, 0),
                    new LineSegment(width, 0, 0, 0)
            ).collect(Collectors.toSet())
        );
    }

    @Override
    public Ball trigger() {
        // Makes the absorber fire a single ball
        if (balls.size() > 0) {
            Ball ball = this.balls.iterator().next();
            this.balls.remove(ball);
            ball.setVelocity(new Vect(0, -50));
            return ball;
        }
        return null;
    }

    @Override
    public boolean containsBall(ReadBall ball) {
        return this.getX() <= ball.getX() &&
               ball.getX() < this.getX() + this.getWidth() &&
               this.getY() <= ball.getY() &&
               ball.getY() < this.getY() + this.getHeight();
    }

    @Override
    public Ball ballHit(Ball ball) {
        ball.setVelocity(new Vect(0, 0));
        // Slight offset to the ball position to avoid floating-point errors causing unexpected collisions
        // with adjacent gizmos.
        ball.setPosition(new Vect(this.getX() + width - Ball.RADIUS - 0.0005,
                                  this.getY() + height - Ball.RADIUS - 0.0005));
        this.balls.add(ball);
        return null;
    }

    private static final double circleOffset = 0.1;
    private static final double circleSize = 0.09;
    @Override
    public Set<Circle> getCircles() {
        return Collections.unmodifiableSet(
                Stream.of(
                    new Circle(circleOffset, circleOffset, circleSize),
                    new Circle(circleOffset, getHeight()-circleOffset, circleSize),
                    new Circle(getWidth()-circleOffset, getHeight()-circleOffset, circleSize),
                    new Circle(getWidth()-circleOffset, circleOffset, circleSize)).collect(Collectors.toSet()));
    }

    @Override
    public void rotate() throws NonRotatableException {
        throw new NonRotatableException("Absorbers cannot be rotated");
    }
}
