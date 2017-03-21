package gizmoball.view;

import gizmoball.controller.*;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.GizmoType;

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
    private SpinnerNumberModel frictionMuModel;
    private SpinnerNumberModel frictionMu2Model;
    private SpinnerNumberModel gravityModel;

    private void addRadioButton(String label, ActionListener controller) {
        JRadioButton button = new JRadioButton();
        button.setFocusable(false);
        button.setText(label);
        button.addActionListener(controller);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        displayLabel.setForeground(Color.RED);
        displayLabel.setFont(new Font("Serif", Font.PLAIN, 17));
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
        addRadioButton("Trigger on gizmo", controller.getSwitchToConnectGizmosListener(this.board, this, this.model));
        addRadioButton("Trigger on keypress", controller.getSwitchToConnectKeyPressListener(this.board, this, this.model));
        addRadioButton("Trigger on keyrelease", controller.getSwitchToConnectKeyReleaseListener(this.board, this,this.model));
        addRadioButton("Trigger on outer wall", controller.getSwitchToConnectOuterwallListener(this.board, this, this.model));

        frictionMuModel = new SpinnerNumberModel(0.025, 0, 1.0, 0.05);
        addSpinner("Friction mu", controller.getChangeFrictionMuListener(this.model, frictionMuModel), frictionMuModel);

        frictionMu2Model = new SpinnerNumberModel(0.025, 0, 1.0, 0.05);
        addSpinner("Friction mu2", controller.getChangeFrictionMu2Listener(this.model, frictionMu2Model), frictionMu2Model);

        gravityModel = new SpinnerNumberModel(25, -100, 100, 1);
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

        frictionMuModel.setValue(model.getFrictionMu());
        frictionMu2Model.setValue(model.getFrictionMu2());
        gravityModel.setValue(model.getGravity());
    }

    //Used to prompt the user to enter a velocity value
    @Override
    public boolean promptVelocity() {
        JTextField velocityXField = new JTextField("0");
        JTextField velocityYField = new JTextField("0");
        Object[] message = {"Enter the velocity x value: ", velocityXField,
                            "Enter the velocity y value: ", velocityYField};
        int optionChosen = JOptionPane.showConfirmDialog(null, message, "Set ball velocity", JOptionPane.OK_CANCEL_OPTION);
        
        
        if (optionChosen == JOptionPane.OK_OPTION) {
            try
            {
                this.promptedVelocityX = Double.parseDouble(velocityXField.getText());
                this.promptedVelocityY = Double.parseDouble(velocityYField.getText());
                return true;
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Value must numeric.", "Velocity value error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
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

    @Override
    public void setCursor(CustomCursorType c) {
        switch (c) {
            case BALL:
            case GIZMO:
                board.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case OPERATIONS:
                board.setCursor(new Cursor(Cursor.HAND_CURSOR));
                break;
            case DEFAULT:
                board.setCursor(Cursor.getDefaultCursor());
                break;
        }
    }

    @Override
    public void displayErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
