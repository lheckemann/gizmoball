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
