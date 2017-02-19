package gizmoball.view;

import gizmoball.controller.KeyTriggerListener;
import gizmoball.controller.TickListener;
import gizmoball.controller.ToggleRunningListener;
import gizmoball.model.RunModel;

import javax.swing.*;

public class RunView extends GameView {
    private JButton stateBtn;
    private JButton tickBtn;

    public RunView(RunModel model) {
        TickListener ticks = new TickListener(model, this);
        this.stateBtn = new JButton("Run"); // either Run or Stop
        this.stateBtn.setFocusable(false);
        this.stateBtn.addActionListener(new ToggleRunningListener(ticks, this));
        this.tickBtn = new JButton("Tick");
        this.tickBtn.setFocusable(false);
        this.tickBtn.addActionListener(ticks);

        JPanel buttonsPnl = new JPanel();
        buttonsPnl.add(stateBtn);
        buttonsPnl.add(tickBtn);

        this.box = new Box(BoxLayout.Y_AXIS);
        this.board = new RunBoardView(model);
        this.box.add(this.board);
        this.box.add(buttonsPnl);
        this.board.addKeyListener(new KeyTriggerListener(model));
        this.board.setFocusable(true);
    }

    public void changeButtonState() {
        if (stateBtn.getText().equals("Run")) {
            stateBtn.setText("Stop");
            tickBtn.setEnabled(false);
        } else {
            stateBtn.setText("Run");
            tickBtn.setEnabled(true);
        }
    }

    public void focus() {
        this.board.requestFocusInWindow();
    }
}
