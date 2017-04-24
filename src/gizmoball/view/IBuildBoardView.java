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
package gizmoball.view;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.GizmoType;

public interface IBuildBoardView {
    void switchToAddBall(IBuildView view, BuildModel model);
    void switchToConnectGizmos(IBuildView view, BuildModel model);
    void switchToConnectKeyPressGizmo(IBuildView view, BuildModel model);
    void switchToConnectKeyReleaseGizmo(IBuildView view, BuildModel model);
    void switchToAddGizmo(GizmoType type, IBuildView view, BuildModel model);
    void switchToDelete(IBuildView view, BuildModel model);
    void switchToMove(IBuildView view, BuildModel model);
    void switchToRotate(IBuildView view, BuildModel model);
    void switchToConnectOuterwall(IBuildView view, BuildModel model);
}
