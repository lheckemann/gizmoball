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
package gizmoball.view;

import gizmoball.controller.Controller;
import gizmoball.model.RunModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunView extends GameView implements IRunView {
    private RunBoardView board;
    private JButton stateBtn;
    private JButton tickBtn;

    private ActionListener toggleRunning;

    public RunView(RunModel model, Controller controller) {
        Timer timer = new Timer((int) (1000 * RunModel.SECONDS_PER_TICK), null);
        toggleRunning = controller.getToggleRunningListener(timer, this);
        ActionListener ticks = controller.getTickListener(timer, model, this);
        timer.addActionListener(ticks);

        stateBtn = new JButton("Run"); // either Run or Stop
        stateBtn.setFocusable(false);
        stateBtn.addActionListener(toggleRunning);
        tickBtn = new JButton("Tick");
        tickBtn.setFocusable(false);
        tickBtn.addActionListener(ticks);

        JPanel buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new BoxLayout(buttonsPnl, BoxLayout.Y_AXIS));
        buttonsPnl.add(stateBtn);
        buttonsPnl.add(tickBtn);
        buttonsPnl.add(Box.createGlue());

        box = new Box(BoxLayout.X_AXIS);
        board = new RunBoardView(model);
        box.add(board);
        box.add(buttonsPnl);

        buttonsPnl.setPreferredSize(new Dimension(this.panelWidth, box.getHeight()));

        board.addKeyListener(controller.getKeyTriggerListener(model));
        board.setFocusable(true);
    }

    @Override
    public void changeButtonState() {
        if (stateBtn.getText().equals("Run")) {
            stateBtn.setText("Stop");
            tickBtn.setEnabled(false);
        } else {
            stateBtn.setText("Run");
            tickBtn.setEnabled(true);
        }
    }

    @Override
    public void updateBoard() {
        this.board.updateUI();
    }

    public void pause() {
        if (stateBtn.getText().equals("Stop"))
            toggleRunning.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    public void focus() {
        board.requestFocusInWindow();
    }
}
