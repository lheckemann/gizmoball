/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
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
        long pre = System.currentTimeMillis();
        long computed = (int) (1000 * this.model.tick());
        this.view.updateBoard();
        /* Attempt to compensate for the time spend computing and drawing */
        int delay = (int) Math.max(0, computed - (System.currentTimeMillis() - pre));
        this.timer.setDelay(delay);
    }
}
