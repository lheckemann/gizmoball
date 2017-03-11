package gizmoball.view;

import gizmoball.controller.Controller;
import gizmoball.model.RunModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RunView extends GameView implements IRunView {
    private RunBoardView board;
    private JButton stateBtn;
    private JButton tickBtn;

    public RunView(RunModel model, Controller controller) {
        ActionListener ticks = controller.getTickListener(model, this);

        stateBtn = new JButton("Run"); // either Run or Stop
        stateBtn.setFocusable(false);
        stateBtn.addActionListener(controller.getToggleRunningListener(ticks, this));
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
}
