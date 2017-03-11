package gizmoball.view;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo;

public interface IBuildBoardView {
    public void switchToAddBall(IBuildView view, BuildModel model);
    public void switchToConnectGizmos(IBuildView view, BuildModel model);
    public void switchToConnectKeyPressGizmo(BuildModel model);
    public void switchToConnectKeyReleaseGizmo(BuildModel model);
    public void switchToAddGizmo(ReadGizmo.GizmoType type, IBuildView view, BuildModel model);
    public void switchToDelete(IBuildView view, BuildModel model);
    public void switchToMove(IBuildView view, BuildModel model);
    public void switchToRotate(IBuildView view, BuildModel model);
}