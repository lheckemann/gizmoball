package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.IBuildBoardView;
import gizmoball.view.IBuildView;

public class SwitchToConnectGizmosListener implements ActionListener {

    private IBuildBoardView board;
    private IBuildView view;
    private BuildModel model;
    
    public SwitchToConnectGizmosListener(IBuildBoardView board, IBuildView buildView, BuildModel model) {
        this.board = board;
        this.view = buildView;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.board.switchToConnectGizmos(view, model);
    }


}
