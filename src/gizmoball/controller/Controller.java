package gizmoball.controller;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.model.RunModel;
import gizmoball.model.gizmos.ReadGizmo;
import gizmoball.view.*;

import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class Controller {
    public MouseListener getAddBallListener(IBuildView view, BuildModel model) {
        return new AddBallListener(view, model);
    }

    public DocumentListener getChangeFrictionListener(BuildModel model, IBuildView view) {
        return new ChangeFrictionListener(model, view);
    }

    public DocumentListener getChangeGravityListener(BuildModel model, IBuildView view) {
        return new ChangeGravityListener(model, view);
    }

    public MouseListener getConnectGizmosListener(IBuildView view, BuildModel model) {
        return new ConnectGizmosListener(view, model);
    }

    public EventListener getConnectKeyPressGizmoListener(BuildModel model) {
        return new ConnectKeyPressGizmoListener(model);
    }

    public EventListener getConnectKeyReleaseGizmoListener(BuildModel model) {
        return new ConnectKeyReleaseGizmoListener(model);
    }

    public EventListener getCreateGizmoListener(ReadGizmo.GizmoType type, IBuildView view, BuildModel model) {
        return new CreateGizmoListener(type, view, model);
    }

    public MouseListener getDeleteGizmoListener(IBuildView view, BuildModel model) {
        return new DeleteGizmoListener(view, model);
    }

    public KeyListener getKeyTriggerListener(RunModel model) {
        return new KeyTriggerListener(model);
    }

    public ActionListener getLoadListener(Model model, IGizmoBallView view) {
        return new LoadListener(model, view);
    }

    public MouseListener getMoveGizmoListener(IBuildView view, BuildModel model) {
        return new MoveGizmoListener(view, model);
    }

    public ActionListener getNewListener(Model model, IGizmoBallView view) {
        return new NewListener(model, view);
    }

    public MouseListener getRotateActionListener(IBuildView view, BuildModel model) {
        return new RotateGizmoListener(view, model);
    }

    public ActionListener getSaveListener(Model model, IGizmoBallView view) {
        return new SaveListener(model, view);
    }

    public ActionListener getSwitchModeListener(IGizmoBallView view) {
        return new SwitchModeListener(view);
    }

    public ActionListener getSwitchToAddBallListener(IBuildBoardView board, IBuildView view, BuildModel model) {
        return new SwitchToAddBallListener(board, view, model);
    }

    public ActionListener getSwitchToConnectGizmosListener(IBuildBoardView board, IBuildView view, BuildModel model) {
        return new SwitchToConnectGizmosListener(board, view, model);
    }

    public ActionListener getSwitchToConnectKeyPressListener(IBuildBoardView view, BuildModel model) {
        return new SwitchToConnectKeyPressGizmoListener(view, model);
    }

    public ActionListener getSwitchToConnectKeyReleaseListener(IBuildBoardView view, BuildModel model) {
        return new SwitchToConnectKeyReleaseGizmoListener(view, model);
    }

    public ActionListener getSwitchToCreateActionListener(ReadGizmo.GizmoType type, IBuildBoardView board, IBuildView view, BuildModel model) {
        return new SwitchToCreateActionListener(type, board, view, model);
    }

    public ActionListener getSwitchToDeleteActionListener(IBuildBoardView board, IBuildView view, BuildModel model) {
        return new SwitchToDeleteActionListener(board, view, model);
    }

    public ActionListener getSwitchToMoveActionListener(IBuildBoardView board, IBuildView view, BuildModel model) {
        return new SwitchToMoveActionListener(board, view, model);
    }

    public ActionListener getSwitchToRotateActionListener(IBuildBoardView board, IBuildView view, BuildModel model) {
        return new SwitchToRotateActionListener(board, view, model);
    }

    public ActionListener getTickListener(RunModel model, IRunView view) {
        return new TickListener(model, view);
    }

    public ActionListener getToggleRunningListener(ActionListener tickListener, IRunView view) {
        return new ToggleRunningListener(tickListener, view);
    }
}