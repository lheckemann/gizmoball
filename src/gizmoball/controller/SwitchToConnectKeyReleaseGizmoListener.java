package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BuildBoardView;
import gizmoball.view.BuildView;

public class SwitchToConnectKeyReleaseGizmoListener implements ActionListener {
    
    private BuildBoardView board;
    private BuildModel model;

    public SwitchToConnectKeyReleaseGizmoListener(BuildBoardView board, BuildModel model) {
        this.board = board;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.switchToConnectKeyReleaseGizmo(model);
    }

}
