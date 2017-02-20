package gizmoball.view;

import gizmoball.controller.LoadListener;
import gizmoball.controller.SaveListener;
import gizmoball.controller.SwitchModeListener;
import gizmoball.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class GizmoBallView {
    private Model model;
    private JFrame frame;
    private JButton modeBtn;
    private GameView gameView;
    private BuildView buildView;
    private RunView runView;
    private JPanel gamePanel;

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
        saveBtn.addActionListener(new SaveListener(model));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.modeBtn.addActionListener(new SwitchModeListener(this));
        actionBar.add(newBtn);
        actionBar.add(loadBtn);
        actionBar.add(saveBtn);
        actionBar.add(modeBtn);
        actionBar.add(Box.createGlue());
        actionBar.add(exitBtn);
        this.buildView = new BuildView(model);
        this.runView = new RunView(model);
        this.gameView = buildView;
        this.frame.add(actionBar, BorderLayout.NORTH);
        gamePanel = new JPanel();
        gamePanel.add(gameView.getBox());
        frame.add(gamePanel);
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public JFrame getGUI() {
        return this.frame;
    }

    public void switchToBuildView() {
        this.modeBtn.setText("Run");
        gamePanel.removeAll();
        this.gameView = buildView;
        gamePanel.add(gameView.getBox());
        this.frame.repaint();
    }

    public void switchToRunView() {
        this.modeBtn.setText("Build");
        gamePanel.removeAll();
        this.gameView = runView;
        gamePanel.add(gameView.getBox());
        runView.focus();
        this.frame.repaint();
    }

    public File getFileByChooser() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    public void displayErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void updateBoard() {
        this.gameView.updateBoard();
    }
}