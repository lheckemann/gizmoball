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

public interface ReadGizmo {

    /**
     * Returns the type of gizmo.
     * Mainly used for loading and saving purposes.
     */
    GizmoType getType();

    /**
     * Returns the current rotation.
     */
    Rotation getRotation();

    /**
     * Returns the current width.
     */
    int getWidth();

    /**
     * Returns the current height.
     */
    int getHeight();

    /**
     * Returns the current location on the X axis.
     */
    int getX();

    /**
     * Returns the current location on the Y axis.
     */
    int getY();

    /**
     * Get the reflection coefficient for colliding balls.
     */
    double getReflectionCoefficient();

    /**
     * Returns the transform that will correctly place the gizmo in the arena.
     * This transform is used both for collision detection and for drawing
     * purposes. The transform is responsible for correctly placing and rotating
     * the gizmo. No further transforms will be applied to the gizmo.
     */
    AffineTransform getTransform();

    /**
     * Get the representing set of line segments.
     * The affine transform returned by `getTransform()` is applied to it.
     */
    Set<LineSegment> getLineSegments();

    /**
     * Get the representing set of circles.
     * The affine transform returned by `getTransform()` is applied to it.
     */
    Set<physics.Circle> getCircles();

    /**
     * Checks whether the given ball is inside the area delimited by the gizmo.
     */
    boolean containsBall(ReadBall ball);

    /**
     * Get the point on which the gizmo pivots.
     */
    Vect getPivot();

    /**
     * Get the angular velocity at which the gizmo pivots.
     */
    Double getAngularVelocity();

    /**
     * Get the set of cells this gizmo occupies.
     */
    Set<Vect> getCells();

    boolean equals(Object obj);
}
