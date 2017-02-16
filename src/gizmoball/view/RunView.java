package gizmoball.view;

import gizmoball.controller.KeyTriggerListener;
import gizmoball.controller.TickListener;
import gizmoball.controller.ToggleRunningListener;
import gizmoball.model.IModel;
import gizmoball.model.RunModel;

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
        buttonsPnl.setFocusable(true);
        buttonsPnl.requestFocusInWindow();
        stateBtn = new JButton("Run"); // either Run or Stop
        stateBtn.setFocusable(false);
        stateBtn.addActionListener(new ToggleRunningListener(new TickListener((RunModel)model, this), this));
        tickBtn = new JButton("Tick");
        tickBtn.setFocusable(false);
        buttonsPnl.add(stateBtn);
        buttonsPnl.add(tickBtn);

        box.add(board);
        box.add(buttonsPnl);
        
        buttonsPnl.addKeyListener(new KeyTriggerListener((RunModel) model));
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
    
    public void updateBoard() {
    	this.board.repaint();
    }

}
