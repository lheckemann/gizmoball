package gizmoball.view;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.GizmoType;

public interface IBuildBoardView {
    public void switchToAddBall(IBuildView view, BuildModel model);
    public void switchToConnectGizmos(IBuildView view, BuildModel model);
    public void switchToConnectKeyPressGizmo(IBuildView view, BuildModel model);
    public void switchToConnectKeyReleaseGizmo(IBuildView view, BuildModel model);
    public void switchToAddGizmo(GizmoType type, IBuildView view, BuildModel model);
    public void switchToDelete(IBuildView view, BuildModel model);
    public void switchToMove(IBuildView view, BuildModel model);
    public void switchToRotate(IBuildView view, BuildModel model);
}
