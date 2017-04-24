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

import gizmoball.model.Ball;
import physics.*;
import physics.Circle;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class Spawner extends BaseGizmo {
    @Override
    public GizmoType getType() {
        return GizmoType.SPAWNER;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public Set<LineSegment> getLineSegments() {
        return Collections.emptySet();
    }

    @Override
    public Set<Circle> getCircles() {
        return Collections.emptySet();
    }

    private static final Random random = new Random();
    @Override
    public Ball trigger() {
        Ball ret = new Ball();
        ret.setPosition(new Vect(getX() + 0.5, getY() + 0.5));
        ret.setVelocityX(random.nextDouble() * 1e-10 - 5e-11);
        ret.setVelocityY(random.nextDouble() * 1e-10 - 5e-11);
        return ret;
    }
}
