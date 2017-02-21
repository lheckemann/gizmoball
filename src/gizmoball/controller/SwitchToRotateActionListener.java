package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BuildBoardView;
import gizmoball.view.BuildView;

public class SwitchToRotateActionListener implements ActionListener {

    private BuildBoardView board;
    private BuildView view;
    private BuildModel model;
    
    public SwitchToRotateActionListener(BuildBoardView board, BuildView buildView, BuildModel model) {
        this.board = board;
        this.view = buildView;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.board.switchToRotate(view, model);
    }

}
