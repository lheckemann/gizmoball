package gizmoball.controller;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.model.RunModel;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.view.*;
import gizmoball.controller.run.*;
import gizmoball.controller.build.*;
import gizmoball.controller.save.*;
import gizmoball.controller.load.*;

public class Controller {
    public MouseListener getAddBallListener(IBuildView view, BuildModel model) {
        return new AddBallListener(view, model);
    }

    public ChangeListener getChangeFrictionMuListener(BuildModel model, SpinnerNumberModel value) {
        return new ChangeFrictionMuListener(model, value);
    }

    public ChangeListener getChangeFrictionMu2Listener(BuildModel model, SpinnerNumberModel value) {
        return new ChangeFrictionMu2Listener(model, value);
    }

    public ChangeListener getChangeGravityListener(BuildModel model, SpinnerNumberModel value) {
        return new ChangeGravityListener(model, value);
    }

    public MouseListener getConnectGizmosListener(IBuildView view, BuildModel model) {
        return new ConnectGizmosListener(view, model);
    }

    public KeyAndMouseListener getConnectKeyPressGizmoListener(IBuildView view, BuildModel model) {
        return new ConnectKeyPressGizmoListener(view, model);
    }

    public KeyAndMouseListener getConnectKeyReleaseGizmoListener(IBuildView view, BuildModel model) {
        return new ConnectKeyReleaseGizmoListener(view, model);
    }

    public MouseInputListener getCreateGizmoListener(GizmoType type, IBuildView view, BuildModel model) {
        return new CreateGizmoListener(type, view, model);
    }

    public MouseInputListener getDeleteGizmoListener(IBuildView view, BuildModel model) {
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

    public ActionListener getSwitchToConnectKeyPressListener(IBuildBoardView buildBoardView, IBuildView buildView, BuildModel model) {
        return new SwitchToConnectKeyPressGizmoListener(buildBoardView, model, buildView);
    }

    public ActionListener getSwitchToConnectKeyReleaseListener(IBuildBoardView buildBoardView, IBuildView buildView, BuildModel model) {
        return new SwitchToConnectKeyReleaseGizmoListener(buildBoardView, model, buildView);
    }

    public ActionListener getSwitchToCreateActionListener(GizmoType type, IBuildBoardView board, IBuildView view, BuildModel model) {
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

    public ActionListener getTickListener(Timer timer, RunModel model, IRunView view) {
        return new TickListener(timer, model, view);
    }

    public ActionListener getToggleRunningListener(Timer timer, IRunView view) {
        return new ToggleRunningListener(timer, view);
    }

    public ActionListener getSwitchToConnectOuterwallListener(BuildBoardView board, BuildView buildView, BuildModel model) {
        return new SwitchToConnectOuterwallListener(board, buildView, model);
    }

    public MouseListener getConnectOuterwallListener(IBuildView view, BuildModel model) {
        return new ConnectOuterwallListener(view, model);
    }

    public Saver getExtendedSaver(BuildModel model) {
        return new Saver(model);
    }

    public Saver getStandardSaver(BuildModel model) {
        return new StandardSaver(model);
    }
}
