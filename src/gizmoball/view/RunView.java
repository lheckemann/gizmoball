package gizmoball.view;

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
        stateBtn = new JButton("Run"); // either Run or Stop
        stateBtn.addActionListener(new ToggleRunningListener(this));
        tickBtn = new JButton("Tick");
        buttonsPnl.add(stateBtn);
        buttonsPnl.add(tickBtn);

        box.add(board);
        box.add(buttonsPnl);
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
