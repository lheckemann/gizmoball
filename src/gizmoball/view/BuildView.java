package gizmoball.view;

import gizmoball.controller.ChangeFrictionListener;
import gizmoball.controller.ChangeGravityListener;
import gizmoball.controller.SwitchToAddBallListener;
import gizmoball.controller.SwitchToConnectGizmosListener;
import gizmoball.controller.SwitchToConnectKeyPressGizmoListener;
import gizmoball.controller.SwitchToConnectKeyReleaseGizmoListener;
import gizmoball.controller.SwitchToCreateActionListener;
import gizmoball.controller.SwitchToDeleteActionListener;
import gizmoball.controller.SwitchToMoveActionListener;
import gizmoball.controller.SwitchToRotateActionListener;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import javax.swing.*;
import java.awt.*;

public class BuildView extends GameView {
    private BuildModel model;
    
    private JRadioButton addBtn;

    private JTextField gravityTxt;
    private JTextField frictionMUTxt;
    private JTextField frictionMU2Txt;
    
    private double promptedVelocityX = 0;
    private double promptedVelocityY = 0;

    public BuildView(BuildModel model) {
        this.model = model;

        box = new Box(BoxLayout.X_AXIS);
        board = new BuildBoardView(model);
        JPanel buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new BoxLayout(buttonsPnl, BoxLayout.Y_AXIS));

        ButtonGroup bg = new ButtonGroup();
        JRadioButton moveBtn = new JRadioButton();
        moveBtn.setFocusable(false);
        moveBtn.setText("Move");
        moveBtn.addActionListener(new SwitchToMoveActionListener((BuildBoardView)this.board, this, this.model));
        JRadioButton deleteBtn = new JRadioButton();
        deleteBtn.setFocusable(false);
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new SwitchToDeleteActionListener((BuildBoardView)this.board, this, this.model));
        JRadioButton rotateBtn = new JRadioButton();
        rotateBtn.setFocusable(false);
        rotateBtn.setText("Rotate");
        rotateBtn.addActionListener(new SwitchToRotateActionListener((BuildBoardView)this.board, this, this.model));
        
        JRadioButton addTriangleBtn = new JRadioButton();
        addTriangleBtn.setFocusable(false);
        addTriangleBtn.setText("Add Triangle");
        addTriangleBtn.addActionListener(new SwitchToCreateActionListener(GizmoType.TRIANGLE, (BuildBoardView)this.board, this, this.model));
        JRadioButton addSquareBtn = new JRadioButton();
        addSquareBtn.setFocusable(false);
        addSquareBtn.setText("Add Square");
        addSquareBtn.addActionListener(new SwitchToCreateActionListener(GizmoType.SQUARE, (BuildBoardView)this.board, this, this.model));
        JRadioButton addCircleBtn = new JRadioButton();
        addCircleBtn.setFocusable(false);
        addCircleBtn.setText("Add Circle");
        addCircleBtn.addActionListener(new SwitchToCreateActionListener(GizmoType.CIRCLE, (BuildBoardView)this.board, this, this.model));
        JRadioButton addRightFlipperBtn = new JRadioButton();
        addRightFlipperBtn.setFocusable(false);
        addRightFlipperBtn.setText("Add Right Flipper");
        addRightFlipperBtn.addActionListener(new SwitchToCreateActionListener(GizmoType.RIGHT_FLIPPER, (BuildBoardView)this.board, this, this.model));
        JRadioButton addLeftFlipperBtn = new JRadioButton();
        addLeftFlipperBtn.setFocusable(false);
        addLeftFlipperBtn.setText("Add Left Flipper");
        addLeftFlipperBtn.addActionListener(new SwitchToCreateActionListener(GizmoType.LEFT_FLIPPER, (BuildBoardView)this.board, this, this.model));
        JRadioButton addAbsorberBtn = new JRadioButton("Add Absorber");
        addAbsorberBtn.setFocusable(false);
        addAbsorberBtn.setText("Add Absorber");
        addAbsorberBtn.addActionListener(new SwitchToCreateActionListener(GizmoType.ABSORBER, (BuildBoardView)this.board, this, this.model));
        JRadioButton addBallBtn = new JRadioButton("Add ball");
        addBallBtn.setFocusable(false);
        addBallBtn.setText("Add Ball");
        addBallBtn.addActionListener(new SwitchToAddBallListener((BuildBoardView)this.board, this, this.model));
        JRadioButton connectGizmosBtn = new JRadioButton();
        connectGizmosBtn.setFocusable(false);
        connectGizmosBtn.setText("Connect Gizmo to Gizmo");
        connectGizmosBtn.addActionListener(new SwitchToConnectGizmosListener((BuildBoardView)this.board, this, this.model));
        JRadioButton connectKeyPressGizmoBtn = new JRadioButton();
        connectKeyPressGizmoBtn.setFocusable(false);
        connectKeyPressGizmoBtn.setText("Connect Key Press to Gizmo");
        connectKeyPressGizmoBtn.addActionListener(new SwitchToConnectKeyPressGizmoListener((BuildBoardView)this.board, this.model));
        JRadioButton connectKeyReleaseGizmoBtn = new JRadioButton();
        connectKeyReleaseGizmoBtn.setFocusable(false);
        connectKeyReleaseGizmoBtn.setText("Connect Key Release to Gizmo");
        connectKeyReleaseGizmoBtn.addActionListener(new SwitchToConnectKeyReleaseGizmoListener((BuildBoardView)this.board, this.model));
        
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
        bg.add(addTriangleBtn);
        bg.add(addCircleBtn);
        bg.add(addSquareBtn);
        bg.add(addLeftFlipperBtn);
        bg.add(addRightFlipperBtn);
        bg.add(addAbsorberBtn);
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
        this.board.repaint();

        gravityTxt.setText(String.valueOf(model.getGravity()));
        frictionMUTxt.setText(String.valueOf(model.getFrictionMu()));
        frictionMU2Txt.setText(String.valueOf(model.getFrictionMu2()));
    }

    //Used to prompt the user to enter a velocity value
    public void promptVelocity() {
        JTextField velocityXField = new JTextField();
        JTextField velocityYField = new JTextField();
        Object[] message = {"Enter the velocity x value: ", velocityXField,
                            "Enter the velocity y value: ", velocityYField};
        JOptionPane.showConfirmDialog(null, message, "Set ball velocity", JOptionPane.OK_OPTION);
        this.promptedVelocityX = Double.parseDouble(velocityXField.getText());
        this.promptedVelocityX = Double.parseDouble(velocityYField.getText());
    }
    
    //Gets the x value for velocity entered by the user
    //The default value if no prompt has been made is 0
    public double getPromptedVelocityX() {
        return this.promptedVelocityX;
    }
    
    //Gets the y value for velocity entered by the user
    //The default value if no prompt has been made is 0
    public double getPromptedVelocityY() {
        return this.promptedVelocityY;
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
