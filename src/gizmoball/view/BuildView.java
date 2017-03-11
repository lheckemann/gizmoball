package gizmoball.view;

import gizmoball.controller.*;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildView extends GameView implements IBuildView {
    private BuildBoardView board;
    private BuildModel model;

    private double promptedVelocityX = 0;
    private double promptedVelocityY = 0;

    private Box buttons = new Box(BoxLayout.Y_AXIS);
    private ButtonGroup actionGroup = new ButtonGroup();

    private JPanel physicsPanel = new JPanel();
    private JLabel displayLabel = new JLabel(" ");

    private void addRadioButton(String label, ActionListener controller) {
        JRadioButton button = new JRadioButton();
        button.setFocusable(false);
        button.setText(label);
        button.addActionListener(controller);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionGroup.add(button);
        buttons.add(button);
    }

    private void addSpinner(String label, ChangeListener controller, SpinnerModel model) {
        model.addChangeListener(controller);
        physicsPanel.add(new JLabel(label));
        physicsPanel.add(new JSpinner(model));
    }

    public BuildView(BuildModel model, Controller controller) {
        this.model = model;

        box = new Box(BoxLayout.Y_AXIS);
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.X_AXIS));
        
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
        
        board = new BuildBoardView(model, controller);

        physicsPanel.setLayout(new GridLayout(3, 2));

        addRadioButton("Move", controller.getSwitchToMoveActionListener(this.board, this, this.model));
        addRadioButton("Delete", controller.getSwitchToDeleteActionListener(this.board, this, this.model));
        addRadioButton("Rotate", controller.getSwitchToRotateActionListener(this.board, this, this.model));
        for (GizmoType type : GizmoType.values()) {
            addRadioButton("Add " + type.toString(), controller.getSwitchToCreateActionListener(type, this.board, this, this.model));
        }

        addRadioButton("Add ball", controller.getSwitchToAddBallListener(this.board, this, this.model));
        addRadioButton("Connect Gizmo to Gizmo", controller.getSwitchToConnectGizmosListener(this.board, this, this.model));
        addRadioButton("Connect keypress to Gizmo", controller.getSwitchToConnectKeyPressListener(this.board, this, this.model));
        addRadioButton("Connect key release to Gizmo", controller.getSwitchToConnectKeyReleaseListener(this.board, this,this.model));

        SpinnerNumberModel frictionMuModel = new SpinnerNumberModel(0.025, 0, 1.0, 0.05);
        addSpinner("Friction mu", controller.getChangeFrictionMuListener(this.model, frictionMuModel), frictionMuModel);

        SpinnerNumberModel frictionMu2Model = new SpinnerNumberModel(0.025, 0, 1.0, 0.05);
        addSpinner("Friction mu2", controller.getChangeFrictionMu2Listener(this.model, frictionMu2Model), frictionMu2Model);

        SpinnerNumberModel gravityModel = new SpinnerNumberModel(25, -100, 100, 1);
        addSpinner("Gravity", controller.getChangeGravityListener(this.model, gravityModel), gravityModel);

        physicsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.add(physicsPanel);
        buttons.add(Box.createGlue());
        
        buttons.setPreferredSize(new Dimension(this.panelWidth, box.getHeight()));
        
        boardPanel.add(board);
        boardPanel.add(buttons);
        
        displayPanel.add(displayLabel);
        displayPanel.add(Box.createHorizontalGlue());
        
        box.add(boardPanel);
        box.add(displayPanel);
    }

    @Override
    public void updateBoard()
    {
        this.board.updateUI();
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

    @Override
    public void setDisplayLabel(String displayText) {
        displayLabel.setText(displayText);
    }
}
