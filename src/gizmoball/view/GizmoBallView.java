package gizmoball.view;

import gizmoball.controller.*;
import gizmoball.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GizmoBallView implements IGizmoBallView {
    private JFrame frame;
    private JButton modeBtn;
    private GameView gameView;
    private BuildView buildView;
    private RunView runView;
    private JPanel gamePanel;

    private Controller controller;

    public GizmoBallView(Model model) {
        controller = new Controller();

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
        this.modeBtn = new JButton("Build");
        this.modeBtn.setFocusable(false);
        newBtn.addActionListener(controller.getNewListener(model, this));
        loadBtn.addActionListener(controller.getLoadListener(model, this));
        saveBtn.addActionListener(controller.getSaveListener(model, this));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.modeBtn.addActionListener(controller.getSwitchModeListener(this));

        actionBar.add(newBtn);
        actionBar.add(loadBtn);
        actionBar.add(saveBtn);
        actionBar.add(modeBtn);
        actionBar.add(Box.createGlue());
        actionBar.add(exitBtn);
        this.buildView = new BuildView(model, controller);
        this.runView = new RunView(model, controller);
        this.gameView = runView;
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

    @Override
    public void switchToBuildView() {
        this.modeBtn.setText("Run");
        gamePanel.removeAll();
        this.gameView = buildView;
        gamePanel.add(gameView.getBox());
        this.frame.repaint();
    }

    @Override
    public void switchToRunView() {
        this.modeBtn.setText("Build");
        gamePanel.removeAll();
        this.gameView = runView;
        gamePanel.add(gameView.getBox());
        runView.focus();
        this.frame.repaint();
    }

    @Override
    public File getFileByChooser() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    @Override
    public void displayErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateBoard() {
        this.gameView.updateBoard();
    }
}
