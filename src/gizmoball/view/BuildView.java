package gizmoball.view;

import gizmoball.controller.ToggleGizmoChoiceListener;
import gizmoball.model.BuildModel;

import javax.swing.*;

public class BuildView implements IBoard {
    private JComboBox<String> gizmoList;
    private JRadioButton addBtn;
    private BoardView board;
    private JPanel buttonsPnl;

    private Box box;

    public BuildView(BuildModel model) {
        box = new Box(BoxLayout.Y_AXIS);
        board = new BuildBoardView(model);
        buttonsPnl = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        JRadioButton moveBtn = new JRadioButton();
        moveBtn.setText("Move");
        moveBtn.addActionListener(new ToggleGizmoChoiceListener(this));
        JRadioButton deleteBtn = new JRadioButton();
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new ToggleGizmoChoiceListener(this));
        JRadioButton rotateBtn = new JRadioButton();
        rotateBtn.setText("Rotate");
        rotateBtn.addActionListener(new ToggleGizmoChoiceListener(this));
        JRadioButton connectBtn = new JRadioButton();
        connectBtn.setText("Connect");
        connectBtn.addActionListener(new ToggleGizmoChoiceListener(this));
        addBtn = new JRadioButton();
        addBtn.setText("Add");
        addBtn.addActionListener(new ToggleGizmoChoiceListener(this));
        String[] gizmoTypes = new String[]{"Circle", "Triangle", "Square",
                "Left Flipper", "Right Flipper", "Ball", "Absorber"};
        gizmoList = new JComboBox<String>(gizmoTypes);
        gizmoList.setEnabled(false);
        bg.add(moveBtn);
        bg.add(deleteBtn);
        bg.add(rotateBtn);
        bg.add(connectBtn);
        bg.add(addBtn);
        buttonsPnl.add(moveBtn);
        buttonsPnl.add(moveBtn);
        buttonsPnl.add(deleteBtn);
        buttonsPnl.add(rotateBtn);
        buttonsPnl.add(connectBtn);
        buttonsPnl.add(addBtn);
        buttonsPnl.add(gizmoList);
        box.add(board);
        box.add(buttonsPnl);
    }

    public void toggleGizmoChoiceVisibility() {
        if (addBtn.isSelected())
            gizmoList.setEnabled(true);
        else
            gizmoList.setEnabled(false);
    }

    @Override
    public Box getBox() {
        return box;
    }
    
    public void updateBoard() {
    	this.board.repaint();
    }
}
