package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.IBuildBoardView;

public class SwitchToConnectKeyReleaseGizmoListener implements ActionListener {
    
    private IBuildBoardView board;
    private BuildModel model;

    public SwitchToConnectKeyReleaseGizmoListener(IBuildBoardView board, BuildModel model) {
        this.board = board;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.switchToConnectKeyReleaseGizmo(model);
    }

}
