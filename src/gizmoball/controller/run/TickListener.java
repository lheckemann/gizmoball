package gizmoball.controller.run;

import gizmoball.model.RunModel;
import gizmoball.view.IRunView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickListener implements ActionListener {
    private RunModel model;
    private IRunView view;

    public TickListener(RunModel model, IRunView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.tick();
        this.view.updateBoard();
    }
}
