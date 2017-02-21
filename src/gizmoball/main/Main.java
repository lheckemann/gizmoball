package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        Main.openGUI(model);
    }

    public static void openGUI(Model model) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GizmoBallView gui = new GizmoBallView(model);
                gui.switchToRunView();
                JFrame frame = gui.getGUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

