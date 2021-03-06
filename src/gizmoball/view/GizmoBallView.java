/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
package gizmoball.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import gizmoball.controller.Controller;
import gizmoball.controller.save.Saver;
import gizmoball.model.BuildModel;
import gizmoball.model.Model;


public class GizmoBallView implements IGizmoBallView {
    private JFrame frame;
    private JButton modeBtn;
    private GameView gameView;
    private BuildView buildView;
    private RunView runView;
    private JPanel gamePanel;
    private Controller controller;

    public GizmoBallView(Model model, Controller controller) {
        this.controller = controller;
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

        buildView = new BuildView(model, controller);
        runView = new RunView(model, controller);
        gameView = runView;

        frame.add(actionBar, BorderLayout.NORTH);
        gamePanel = new JPanel();
        gamePanel.add(gameView.getBox()); // we start with runView
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public JFrame getGUI() {
        return this.frame;
    }

    @Override
    public void switchToBuildView() {
        this.modeBtn.setText("Run");
        gamePanel.removeAll();
        runView.pause();
        buildView.updateBoard();
        gameView = buildView;
        gamePanel.add(gameView.getBox());
        frame.pack();
        this.frame.repaint();
    }

    @Override
    public void switchToRunView() {
        this.modeBtn.setText("Build");
        gamePanel.removeAll();
        gameView = runView;
        gamePanel.add(gameView.getBox());
        runView.focus();
        frame.pack();
        this.frame.repaint();
    }

    @Override
    public File getFileByChooserLoad() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    @Override
    public File getFileByChooserSave() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
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
        gameView.updateBoard();
    }

    @Override
    public Saver promptSaverType(BuildModel model) {
        String[] options = new String[] {"Standard", "Extended", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, "Choose saver type", "Select a saver to use",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);

        if (result == 0)
            return controller.getStandardSaver(model);
        if (result == 1)
            return controller.getExtendedSaver(model);
        return null;
    }

    @Override
    public void reset() {
        runView.pause();
        updateBoard();
    }
}
