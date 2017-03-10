package gizmoball.view;

import gizmoball.controller.*;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import javax.swing.*;
import java.awt.*;

public class BuildView extends GameView implements IBuildView {
    private BuildBoardView board;
    private BuildModel model;

    private JTextField gravityTxt;
    private JTextField frictionMUTxt;
    private JTextField frictionMU2Txt;

    private double promptedVelocityX = 0;
    private double promptedVelocityY = 0;

    public BuildView(BuildModel model, Controller controller) {
        this.model = model;

        box = new Box(BoxLayout.X_AXIS);
        board = new BuildBoardView(model, controller);
        JPanel buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new BoxLayout(buttonsPnl, BoxLayout.Y_AXIS));

        ButtonGroup bg = new ButtonGroup();

        JRadioButton moveBtn = new JRadioButton();
        moveBtn.setFocusable(false);
        moveBtn.setText("Move");
        moveBtn.addActionListener(controller.getSwitchToMoveActionListener(this.board, this, this.model));

        JRadioButton deleteBtn = new JRadioButton();
        deleteBtn.setFocusable(false);
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(controller.getSwitchToDeleteActionListener((BuildBoardView)this.board, this, this.model));

        JRadioButton rotateBtn = new JRadioButton();
        rotateBtn.setFocusable(false);
        rotateBtn.setText("Rotate");
        rotateBtn.addActionListener(controller.getSwitchToRotateActionListener(this.board, this, this.model));

        JRadioButton addTriangleBtn = new JRadioButton();
        addTriangleBtn.setFocusable(false);
        addTriangleBtn.setText("Add Triangle");
        addTriangleBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.TRIANGLE, this.board, this, this.model));

        JRadioButton addSquareBtn = new JRadioButton();
        addSquareBtn.setFocusable(false);
        addSquareBtn.setText("Add Square");
        addSquareBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.SQUARE, this.board, this, this.model));

        JRadioButton addCircleBtn = new JRadioButton();
        addCircleBtn.setFocusable(false);
        addCircleBtn.setText("Add Circle");
        addCircleBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.CIRCLE, this.board, this, this.model));

        JRadioButton addRightFlipperBtn = new JRadioButton();
        addRightFlipperBtn.setFocusable(false);
        addRightFlipperBtn.setText("Add Right Flipper");
        addRightFlipperBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.RIGHT_FLIPPER, this.board, this, this.model));

        JRadioButton addLeftFlipperBtn = new JRadioButton();
        addLeftFlipperBtn.setFocusable(false);
        addLeftFlipperBtn.setText("Add Left Flipper");
        addLeftFlipperBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.LEFT_FLIPPER, this.board, this, this.model));

        JRadioButton addAbsorberBtn = new JRadioButton("Add Absorber");
        addAbsorberBtn.setFocusable(false);
        addAbsorberBtn.setText("Add Absorber");
        addAbsorberBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.ABSORBER, this.board, this, this.model));

        JRadioButton addSpawnerBtn = new JRadioButton("Add spawner");
        addSpawnerBtn.setFocusable(false);
        addSpawnerBtn.setText("Add Spawner");
        addSpawnerBtn.addActionListener(controller.getSwitchToCreateActionListener(GizmoType.SPAWNER, this.board, this, this.model));

        JRadioButton addBallBtn = new JRadioButton("Add ball");
        addBallBtn.setFocusable(false);
        addBallBtn.setText("Add Ball");
        addBallBtn.addActionListener(controller.getSwitchToAddBallListener(this.board, this, this.model));

        JRadioButton connectGizmosBtn = new JRadioButton();
        connectGizmosBtn.setFocusable(false);
        connectGizmosBtn.setText("Connect Gizmo to Gizmo");
        connectGizmosBtn.addActionListener(controller.getSwitchToConnectGizmosListener(this.board, this, this.model));

        JRadioButton connectKeyPressGizmoBtn = new JRadioButton();
        connectKeyPressGizmoBtn.setFocusable(false);
        connectKeyPressGizmoBtn.setText("Connect Key Press to Gizmo");
        connectKeyPressGizmoBtn.addActionListener(controller.getSwitchToConnectKeyPressListener(this.board, this.model));

        JRadioButton connectKeyReleaseGizmoBtn = new JRadioButton();
        connectKeyReleaseGizmoBtn.setFocusable(false);
        connectKeyReleaseGizmoBtn.setText("Connect Key Release to Gizmo");
        connectKeyReleaseGizmoBtn.addActionListener(controller.getSwitchToConnectKeyReleaseListener(this.board, this.model));

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

        bg.add(moveBtn);
        bg.add(deleteBtn);
        bg.add(rotateBtn);
        bg.add(addTriangleBtn);
        bg.add(addCircleBtn);
        bg.add(addSquareBtn);
        bg.add(addLeftFlipperBtn);
        bg.add(addRightFlipperBtn);
        bg.add(addAbsorberBtn);
        bg.add(addSpawnerBtn);
        bg.add(addBallBtn);
        bg.add(connectGizmosBtn);
        bg.add(connectKeyPressGizmoBtn);
        bg.add(connectKeyReleaseGizmoBtn);
        buttonsPnl.add(moveBtn);
        buttonsPnl.add(moveBtn);
        buttonsPnl.add(deleteBtn);
        buttonsPnl.add(rotateBtn);
        buttonsPnl.add(addTriangleBtn);
        buttonsPnl.add(addCircleBtn);
        buttonsPnl.add(addSquareBtn);
        buttonsPnl.add(addLeftFlipperBtn);
        buttonsPnl.add(addRightFlipperBtn);
        buttonsPnl.add(addAbsorberBtn);
        buttonsPnl.add(addSpawnerBtn);
        buttonsPnl.add(addBallBtn);
        buttonsPnl.add(connectGizmosBtn);
        buttonsPnl.add(connectKeyPressGizmoBtn);
        buttonsPnl.add(connectKeyReleaseGizmoBtn);
        buttonsPnl.add(gravityPnl);
        buttonsPnl.add(frictionMUPnl);
        buttonsPnl.add(frictionMU2Pnl);
        buttonsPnl.add(Box.createGlue());

        buttonsPnl.setPreferredSize(new Dimension(this.panelWidth, box.getHeight()));

        box.add(board);
        box.add(buttonsPnl);
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
