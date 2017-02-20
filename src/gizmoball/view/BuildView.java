package gizmoball.view;

import gizmoball.controller.ToggleGizmoChoiceListener;
import gizmoball.model.BuildModel;

import javax.swing.*;

public class BuildView extends GameView {
    private JComboBox<String> gizmoList;
    private JRadioButton addBtn;
    private JPanel buttonsPnl;

    public BuildView(BuildModel model) {
        box = new Box(BoxLayout.X_AXIS);
        board = new BuildBoardView(model);
        buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new BoxLayout(buttonsPnl, BoxLayout.Y_AXIS));
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

        JPanel gravityPnl = new JPanel();
        JLabel gravityLbl = new JLabel("Gravity: ");
        JTextField gravityTxt = new JTextField();
        gravityTxt.setText(String.valueOf(model.getGravity()));
        //
        // add listener to observer change of text
        //
        JPanel frictionMUPnl = new JPanel();
        JLabel frictionMULbl = new JLabel("Friction MU: ");
        JTextField frictionMUTxt = new JTextField();
        frictionMUTxt.setText(String.valueOf(model.getFrictionMu()));
        //
        // add listener to observer change of text
        //
        JPanel frictionMU2Pnl = new JPanel();
        JLabel frictionMU2Lbl = new JLabel("Friction MU2: ");
        JTextField frictionMU2Txt = new JTextField();
        frictionMU2Txt.setText(String.valueOf(model.getFrictionMu2()));
        //
        // add listener to observer change of text
        //

        gravityPnl.add(gravityLbl);
        gravityPnl.add(gravityTxt);
        frictionMUPnl.add(frictionMULbl);
        frictionMUPnl.add(frictionMUTxt);
        frictionMU2Pnl.add(frictionMU2Lbl);
        frictionMU2Pnl.add(frictionMU2Txt);

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
        buttonsPnl.add(gravityPnl);
        buttonsPnl.add(frictionMUPnl);
        buttonsPnl.add(frictionMU2Pnl);
        buttonsPnl.add(Box.createGlue());
        box.add(board);
        box.add(buttonsPnl);
    }

    public void toggleGizmoChoiceVisibility() {
        if (addBtn.isSelected())
            gizmoList.setEnabled(true);
        else
            gizmoList.setEnabled(false);
    }

}
