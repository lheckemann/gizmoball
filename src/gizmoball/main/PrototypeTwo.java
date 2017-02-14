package gizmoball.main;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class PrototypeTwo {
    public static void main(String[] args) {
        BuildModel model = new Model(20, 20);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GizmoBallView gui = new GizmoBallView(model);
                JFrame frame = gui.getGUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
