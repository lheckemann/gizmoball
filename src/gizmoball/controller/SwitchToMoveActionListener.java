package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.view.BuildBoardView;
import gizmoball.view.BuildView;

public class SwitchToMoveActionListener implements ActionListener {

    private BuildBoardView board;
    private BuildView view;
    private BuildModel model;
   
    public SwitchToMoveActionListener(BuildBoardView board, BuildView buildView, BuildModel model) {
        this.board = board;
        this.view = buildView;
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        board.switchToMove(view, model); 
    }

}
