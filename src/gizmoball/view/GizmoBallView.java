package gizmoball.view;

import gizmoball.controller.LoadListener;
import gizmoball.controller.SwitchModeListener;
import gizmoball.model.IModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GizmoBallView {
    private IModel model;
    private JFrame frame;
    private JButton modeBtn;
    private IBoard boardView;

    public GizmoBallView(IModel model) {
        this.model = model;

        frame = new JFrame("Gizmoball");
        Box actionBar = new Box(BoxLayout.X_AXIS);
        JButton newBtn = new JButton("New");
        JButton loadBtn = new JButton("Load");
        JButton saveBtn = new JButton("Save");
        JButton exitBtn = new JButton("Exit");
        modeBtn = new JButton("Run");
        //newBtn.addActionListener(new CreateGizmoListener(model, this));
        loadBtn.addActionListener(new LoadListener(model, this));
        //saveBtn.addActionListener(new LoadListener(model, this));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        modeBtn.addActionListener(new SwitchModeListener(this, model));
        actionBar.add(newBtn);
        actionBar.add(loadBtn);
        actionBar.add(saveBtn);
        actionBar.add(modeBtn);
        actionBar.add(Box.createGlue());
        actionBar.add(exitBtn);
        boardView = new BuildView(model);
        frame.add(actionBar, BorderLayout.NORTH);
        frame.add(boardView.getBox(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public JFrame getGUI() {
        return frame;
    }

    public void switchToBuildView(IModel model) {
        modeBtn.setText("Run");

        frame.getContentPane().remove(boardView.getBox());
        boardView = new BuildView(model);
        frame.getContentPane().add(boardView.getBox(), BorderLayout.CENTER);
    }

    public void switchToRunView(IModel model) {
        modeBtn.setText("Build");

        frame.getContentPane().remove(boardView.getBox());
        boardView = new RunView(model);
        frame.getContentPane().add(boardView.getBox(), BorderLayout.CENTER);
    }

    public void updateGUI() {
        boardView.updateGUI();
    }
}
