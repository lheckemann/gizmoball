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
        box = new Box(BoxLayout.Y_AXIS);
        board = new RunBoardView(model);
        JPanel buttonsPnl = new JPanel();
        buttonsPnl.setFocusable(true);
        buttonsPnl.requestFocusInWindow();
        stateBtn = new JButton("Run"); // either Run or Stop
        stateBtn.setFocusable(false);
        stateBtn.addActionListener(new ToggleRunningListener(new TickListener(model, this), this));
        tickBtn = new JButton("Tick");
        tickBtn.setFocusable(false);
        buttonsPnl.add(stateBtn);
        buttonsPnl.add(tickBtn);

        box.add(board);
        box.add(buttonsPnl);

        buttonsPnl.addKeyListener(new KeyTriggerListener(model));
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
}
