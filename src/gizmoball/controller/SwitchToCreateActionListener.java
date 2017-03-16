package gizmoball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.GizmoType;
import gizmoball.view.IBuildBoardView;
import gizmoball.view.IBuildView;

public class SwitchToCreateActionListener implements ActionListener {

    private GizmoType type;
    private IBuildBoardView board;
    private IBuildView view;
    private BuildModel model;

    public SwitchToCreateActionListener(GizmoType type, IBuildBoardView board, IBuildView buildView,
            BuildModel model) {
        this.type = type;
        this.board = board;
        this.view = buildView;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.board.switchToAddGizmo(type, view, model);
        if (this.type.equals(GizmoType.ABSORBER)) {
            this.view.setDisplayLabel("Click & hold the mouse on a grid location then drag to specify the absorber's size. Release the mouse when finished");
        } else {
            this.view.setDisplayLabel("Click on a grid location to add " + this.type.toString());
        }

    }

}
