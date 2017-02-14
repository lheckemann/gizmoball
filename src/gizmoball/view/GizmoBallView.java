package gizmoball.view;

import gizmoball.controller.LoadListener;
import gizmoball.controller.CreateGizmoListener;
import gizmoball.controller.SwitchModeListener;
import gizmoball.model.BuildModel;
import gizmoball.model.IModel;
import gizmoball.model.RunModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GizmoBallView {
    private IModel model;
    private JFrame frame;
    private JButton modeBtn;
    private Box boardView;

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
        modeBtn.addActionListener(new SwitchModeListener(this));
        actionBar.add(newBtn);
        actionBar.add(loadBtn);
        actionBar.add(saveBtn);
        actionBar.add(modeBtn);
        actionBar.add(Box.createGlue());
        actionBar.add(exitBtn);
        boardView = new BuildView((BuildModel) model);
        frame.add(actionBar, BorderLayout.NORTH);
        frame.add(boardView, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /*public static void main(String[] args) {
        window = new JFrame();
        RunModel model = Model.prototype1Example();
        window.add(new RunView(model).getComponent());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(300, 300));
    }*/

    /*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.gray);
        for (int x = 0; x < 30 * Model.L_TO_PIXELS; x += Model.L_TO_PIXELS)
            g2.drawLine(x, 0, x, 500);
        for (int y = 0; y < 30 * Model.L_TO_PIXELS; y += Model.L_TO_PIXELS)
            g2.drawLine(0, y, 1000, y);
        g2.translate(32, 32);
        // draw flipper
        for (Flipper flipper : flippers) {
            g2.translate(96, 0);
            flipperView.paint(g2, flipper);
        }
    }
    */

    public JFrame getGUI() {
        return frame;
    }

    public void switchToBuildView(BuildModel model) {
        modeBtn.setText("Run");
        frame.remove(boardView);
        boardView = new BuildView(model);
        frame.add(boardView, BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
    }

    public void switchToRunView(RunModel model) {
        modeBtn.setText("Build");
        frame.remove(boardView);
        boardView = new RunView(model);
        frame.add(boardView, BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
    }
}
