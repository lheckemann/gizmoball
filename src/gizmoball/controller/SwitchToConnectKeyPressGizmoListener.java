package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.IBuildBoardView;

public class SwitchToConnectKeyPressGizmoListener implements ActionListener {
    
    private IBuildBoardView board;
    private BuildModel model;

    public SwitchToConnectKeyPressGizmoListener(IBuildBoardView board, BuildModel model) {
        this.board = board;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.switchToConnectKeyPressGizmo(model);
    }

}