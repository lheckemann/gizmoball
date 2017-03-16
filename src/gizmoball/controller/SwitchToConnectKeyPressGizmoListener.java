package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.IBuildBoardView;
import gizmoball.view.IBuildView;

public class SwitchToConnectKeyPressGizmoListener implements ActionListener {
    private IBuildBoardView board;
    private BuildModel model;
    private IBuildView view;

    public SwitchToConnectKeyPressGizmoListener(IBuildBoardView board, BuildModel model, IBuildView view) {
        this.board = board;
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.switchToConnectKeyPressGizmo(view, model);
    }
}
