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

import gizmoball.view.IRunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleRunningListener implements ActionListener {
    private Timer timer;
    private final IRunView view;

    public ToggleRunningListener(Timer timer, IRunView view) {
        this.timer = timer;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timer.isRunning()) {
            timer.stop();
            view.changeButtonState();
        } else {
            timer.start();
            view.changeButtonState();
        }
    }
}
