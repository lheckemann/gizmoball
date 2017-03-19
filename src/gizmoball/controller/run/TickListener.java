package gizmoball.controller.run;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gizmoball.model.RunModel;
import gizmoball.view.IRunView;

public class TickListener implements ActionListener {
    private Timer timer;
    private RunModel model;
    private IRunView view;

    public TickListener(Timer timer, RunModel model, IRunView view) {
        this.timer = timer;
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double elapsed = this.model.tick();
        this.view.updateBoard();
        this.timer.setDelay((int) (1000 * elapsed));
    }
}
