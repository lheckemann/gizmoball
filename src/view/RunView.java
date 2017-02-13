package view;

import controller.TickListener;
import controller.ToggleRunningListener;
import model.RunModel;

import javax.swing.*;
import java.awt.event.ActionListener;

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
