package gizmoball.view;

import gizmoball.controller.*;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildView extends GameView implements IBuildView {
    private BuildBoardView board;
    private BuildModel model;

    private JTextField gravityTxt;
    private JTextField frictionMUTxt;
    private JTextField frictionMU2Txt;

    private double promptedVelocityX = 0;
    private double promptedVelocityY = 0;

    private Box buttons = new Box(BoxLayout.Y_AXIS);
    private ButtonGroup actionGroup = new ButtonGroup();

    private void addRadioButton(String label, ActionListener controller) {
        JRadioButton button = new JRadioButton();
        button.setFocusable(false);
        button.setText(label);
        button.addActionListener(controller);
        actionGroup.add(button);
        buttons.add(button);
    }

    public BuildView(BuildModel model, Controller controller) {
        this.model = model;

        box = new Box(BoxLayout.X_AXIS);
        board = new BuildBoardView(model, controller);

        addRadioButton("Move", controller.getSwitchToMoveActionListener(this.board, this, this.model));
        addRadioButton("Delete", controller.getSwitchToDeleteActionListener(this.board, this, this.model));
        addRadioButton("Rotate", controller.getSwitchToRotateActionListener(this.board, this, this.model));
        for (GizmoType type : GizmoType.values()) {
            addRadioButton("Add " + type.toString(), controller.getSwitchToCreateActionListener(type, this.board, this, this.model));
        }

        addRadioButton("Add ball", controller.getSwitchToAddBallListener(this.board, this, this.model));
        addRadioButton("Connect Gizmo to Gizmo", controller.getSwitchToConnectGizmosListener(this.board, this, this.model));
        addRadioButton("Connect keypress to Gizmo", controller.getSwitchToConnectKeyPressListener(this.board, this.model));
        addRadioButton("Connect key release to Gizmo", controller.getSwitchToConnectKeyReleaseListener(this.board, this.model));

        JPanel gravityPnl = new JPanel();
        JLabel gravityLbl = new JLabel("Gravity: ");
        gravityTxt = new JTextField();
        gravityTxt.setColumns(5);
        gravityTxt.getDocument().addDocumentListener(controller.getChangeGravityListener(model, this));

        JPanel frictionMUPnl = new JPanel();
        JLabel frictionMULbl = new JLabel("Friction MU: ");
        frictionMUTxt = new JTextField();
        frictionMUTxt.setColumns(5);
        frictionMUTxt.getDocument().addDocumentListener(controller.getChangeFrictionListener(model, this));

        JPanel frictionMU2Pnl = new JPanel();
        JLabel frictionMU2Lbl = new JLabel("Friction MU2: ");
        frictionMU2Txt = new JTextField();
        frictionMU2Txt.setColumns(5);
        frictionMU2Txt.getDocument().addDocumentListener(controller.getChangeFrictionListener(model, this));

        gravityPnl.add(gravityLbl);
        gravityPnl.add(gravityTxt);
        frictionMUPnl.add(frictionMULbl);
        frictionMUPnl.add(frictionMUTxt);
        frictionMU2Pnl.add(frictionMU2Lbl);
        frictionMU2Pnl.add(frictionMU2Txt);

        buttons.add(gravityPnl);
        buttons.add(frictionMUPnl);
        buttons.add(frictionMU2Pnl);
        buttons.add(Box.createGlue());

        buttons.setPreferredSize(new Dimension(this.panelWidth, box.getHeight()));

        box.add(board);
        box.add(buttons);
    }

    @Override
    public void updateBoard()
    {
        this.board.updateUI();

        gravityTxt.setText(String.valueOf(model.getGravity()));
        frictionMUTxt.setText(String.valueOf(model.getFrictionMu()));
        frictionMU2Txt.setText(String.valueOf(model.getFrictionMu2()));
    }

    //Used to prompt the user to enter a velocity value
    @Override
    public void promptVelocity() {
        JTextField velocityXField = new JTextField("0");
        JTextField velocityYField = new JTextField("0");
        Object[] message = {"Enter the velocity x value: ", velocityXField,
                            "Enter the velocity y value: ", velocityYField};
        JOptionPane.showConfirmDialog(null, message, "Set ball velocity", JOptionPane.OK_OPTION);
        this.promptedVelocityX = Double.parseDouble(velocityXField.getText());
        this.promptedVelocityY = Double.parseDouble(velocityYField.getText());
    }

    //Gets the x value for velocity entered by the user
    //The default value if no prompt has been made is 0
    @Override
    public double getPromptedVelocityX() {
        return this.promptedVelocityX;
    }

    //Gets the y value for velocity entered by the user
    //The default value if no prompt has been made is 0
    @Override
    public double getPromptedVelocityY() {
        return this.promptedVelocityY;
    }

    // no checks are being made (text must be a number)
    @Override
    public String getGravityText() {
        return gravityTxt.getText();
    }

    @Override
    public String getFrictionMuText() {
        return frictionMUTxt.getText();
    }

    @Override
    public String getFrictionMu2Text() {
        return frictionMU2Txt.getText();
    }
}
