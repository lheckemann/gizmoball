package gizmoball.view;

import gizmoball.controller.LoadListener;
import gizmoball.controller.SwitchModeListener;
import gizmoball.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GizmoBallView {
    private Model model;
    private JFrame frame;
    private JButton modeBtn;
    private GameView gameView;

    public GizmoBallView(Model model) {
        this.model = model;

        this.frame = new JFrame("Gizmoball");
        Box actionBar = new Box(BoxLayout.X_AXIS);
        JButton newBtn = new JButton("New");
        newBtn.setFocusable(false);
        JButton loadBtn = new JButton("Load");
        loadBtn.setFocusable(false);
        JButton saveBtn = new JButton("Save");
        saveBtn.setFocusable(false);
        JButton exitBtn = new JButton("Exit");
        exitBtn.setFocusable(false);
        this.modeBtn = new JButton("Run");
        this.modeBtn.setFocusable(false);
        //newBtn.addActionListener(new CreateGizmoListener(model, this));
        loadBtn.addActionListener(new LoadListener(model, this));
        //saveBtn.addActionListener(new LoadListener(model, this));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.modeBtn.addActionListener(new SwitchModeListener(this, model));
        actionBar.add(newBtn);
        actionBar.add(loadBtn);
        actionBar.add(saveBtn);
        actionBar.add(modeBtn);
        actionBar.add(Box.createGlue());
        actionBar.add(exitBtn);
        this.gameView = new BuildView(model);
        this.frame.add(actionBar, BorderLayout.NORTH);
        this.frame.add(gameView.getBox(), BorderLayout.CENTER);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public JFrame getGUI() {
        return this.frame;
    }

    public void switchToBuildView(Model model) {
        this.modeBtn.setText("Run");
        this.frame.getContentPane().remove(gameView.getBox());
        this.gameView = new BuildView(model);
        this.frame.getContentPane().add(gameView.getBox(), BorderLayout.CENTER);
    }

    public void switchToRunView(Model model) {
        this.modeBtn.setText("Build");
        this.frame.getContentPane().remove(gameView.getBox());
        this.gameView = new RunView(model);
        this.frame.getContentPane().add(gameView.getBox(), BorderLayout.CENTER);
    }

    public void updateBoard() {
    	this.gameView.updateBoard();
    }
}
