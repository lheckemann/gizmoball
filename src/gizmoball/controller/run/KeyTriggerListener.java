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

import gizmoball.model.RunModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyTriggerListener extends KeyAdapter {
    private final RunModel model;
    private final Set<Integer> pressedSet = new HashSet<>();

    public KeyTriggerListener(RunModel model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedSet.contains(e.getKeyCode())) {
            pressedSet.add(e.getKeyCode());
            model.keyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedSet.remove(e.getKeyCode());
        model.keyReleased(e.getKeyCode());
    }
}
