package gizmoball.view;

import gizmoball.controller.ChangeFrictionListener;
import gizmoball.controller.ChangeGravityListener;
import gizmoball.controller.ToggleGizmoChoiceListener;
import gizmoball.model.BuildModel;

import javax.swing.*;

public class BuildView extends GameView {
    private BuildModel model;

    private JComboBox<String> gizmoList;
    private JRadioButton addBtn;
    private JPanel buttonsPnl;

    private JTextField gravityTxt;
    private JTextField frictionMUTxt;
    private JTextField frictionMU2Txt;

    public BuildView(BuildModel model) {
        this.model = model;

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
        gravityTxt = new JTextField();
        gravityTxt.setColumns(5);
        gravityTxt.getDocument().addDocumentListener(new ChangeGravityListener(model, this));

        JPanel frictionMUPnl = new JPanel();
        JLabel frictionMULbl = new JLabel("Friction MU: ");
        frictionMUTxt = new JTextField();
        frictionMUTxt.setColumns(5);
        frictionMUTxt.getDocument().addDocumentListener(new ChangeFrictionListener(model, this));

        JPanel frictionMU2Pnl = new JPanel();
        JLabel frictionMU2Lbl = new JLabel("Friction MU2: ");
        frictionMU2Txt = new JTextField();
        frictionMU2Txt.setColumns(5);
        frictionMU2Txt.getDocument().addDocumentListener(new ChangeFrictionListener(model, this));

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

    @Override
    public void updateBoard()
    {
        this.board.repaint();

        gravityTxt.setText(String.valueOf(model.getGravity()));
        frictionMUTxt.setText(String.valueOf(model.getFrictionMu()));
        frictionMU2Txt.setText(String.valueOf(model.getFrictionMu2()));
    }

    // no checks are being made (text must be a number)
    public String getGravityText() {
        return gravityTxt.getText();
    }

    public String getFrictionMuText() {
        return frictionMUTxt.getText();
    }

    public String getFrictionMu2Text() {
        return frictionMU2Txt.getText();
    }
}
