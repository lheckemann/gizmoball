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

    private long sinceLastRedraw = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        long pre = System.currentTimeMillis();
        long computed = (int) (1000 * this.model.tick());
        sinceLastRedraw += computed;
        if (sinceLastRedraw >= 1000 * RunModel.SECONDS_PER_TICK) {
            this.view.updateBoard();
            sinceLastRedraw = 0;
        }
        /* Attempt to compensate for the time spend computing and drawing */
        int delay = (int) Math.max(0, computed - (System.currentTimeMillis() - pre));
        this.timer.setDelay(delay);
    }
}
