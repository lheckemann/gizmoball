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

import java.awt.geom.AffineTransform;
import java.util.*;

import physics.*;

import gizmoball.model.*;

public abstract class BaseGizmo implements Gizmo {
    private Rotation rotation;
    private int x;
    private int y;

    public BaseGizmo() {
        this.rotation = Rotation.NORTH;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public Rotation getRotation() {
        return this.rotation;
    }

    @Override
    public void rotate() throws NonRotatableException {
        this.rotation = this.rotation.nextCW();
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void tick(double lapse) {
    }

    @Override
    public double getReflectionCoefficient() {
        return 1.0;
    }

    @Override
    public Ball trigger() {
        return null;
    }

    @Override
    public boolean containsBall(ReadBall ball) {
        return false;
    }

    @Override
    public Ball ballHit(Ball ball) {
        return ball;
    }

    @Override
    public AffineTransform getTransform() {
        int m00, m01, m02, m10, m11, m12;
        m00 = m01 = m02 = m10 = m11 = m12 = 0;
        switch (this.getRotation()) {
            case NORTH:
                m00 =  1; m01 =  0; m02 = this.getX();
                m10 =  0; m11 =  1; m12 = this.getY();
                break;
            case EAST:
                m00 =  0; m01 = -1; m02 = this.getHeight() + this.getX();
                m10 =  1; m11 =  0; m12 = this.getY();
                break;
            case SOUTH:
                m00 = -1; m01 =  0; m02 = this.getWidth() + this.getX();
                m10 =  0; m11 =  -1; m12 = this.getHeight() + this.getY();
                break;
            case WEST:
                m00 =  0; m01 =  1; m02 = this.getX();
                m10 = -1; m11 =  0; m12 = this.getWidth() + this.getY();
                break;
        }
        return new AffineTransform(m00, m10, m01, m11, m02, m12);
    }

    @Override
    public Vect getPivot() {
        return new Vect(0, 0);
    }

    @Override
    public Double getAngularVelocity() {
        return 0d;
    }

    @Override
    public Set<Vect> getCells() {
        Set<Vect> cells = new HashSet<>();
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                cells.add(new Vect(this.getX() + x, this.getY() + y));
            }
        }
        return cells;
    }
}
