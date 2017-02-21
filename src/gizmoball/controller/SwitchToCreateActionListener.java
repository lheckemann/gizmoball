package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;
import gizmoball.view.BuildBoardView;
import gizmoball.view.BuildView;

public class SwitchToCreateActionListener implements ActionListener {
    
    private GizmoType type;
    private BuildBoardView board;
    private BuildView view;
    private BuildModel model;
    
    public SwitchToCreateActionListener(GizmoType type, BuildBoardView board, BuildView buildView,
            BuildModel model) {
        this.type = type;
        this.board = board;
        this.view = buildView;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.board.switchToAddGizmo(type, view, model);
    }

}
