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
