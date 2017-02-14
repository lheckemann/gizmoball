package gizmoball.view;

import gizmoball.controller.ToggleRunningListener;
import gizmoball.model.IModel;

import javax.swing.*;

public class RunView implements IBoard {
    private JPanel buttonsPnl;
    private JButton stateBtn;
    private JButton tickBtn;
    private BoardView board;

    private Box box;

    public RunView(IModel model) {
        box = new Box(BoxLayout.Y_AXIS);
        board = new RunBoardView(model);
        buttonsPnl = new JPanel();
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

    @Override
    public Box getBox() {
        return box;
    }

    @Override
    public void updateGUI() {
        board.updateGUI();
    }
}
/*
import gizmoball.controller.TickListener;
import gizmoball.controller.ToggleRunningListener;
import gizmoball.model.RunModel;

import javax.swing.*;
import java.awt.event.ActionListener;

/*
public class RunView {
    private final Box impl;
    private final RunModel model;
    private final JButton toggleRunningButton;

    public RunView(RunModel model) {
        this.model = model;
        impl = new Box(BoxLayout.Y_AXIS);
        Box buttonsBox = new Box(BoxLayout.X_AXIS);
        toggleRunningButton = new JButton("Run");
        buttonsBox.add(toggleRunningButton);
        impl.add(buttonsBox);

        RunBoardView board = new RunBoardView(model);
        impl.add(board);

        ActionListener tickListener = new TickListener(model, board);
        ActionListener toggleRunningListener = new ToggleRunningListener(tickListener, this);
        toggleRunningButton.addActionListener(toggleRunningListener);
    }

    public JComponent getComponent() {
        return impl;
    }

    public void setRunning(boolean running) {
        toggleRunningButton.setText(running ? "Pause" : "Run");
    }
}
*/