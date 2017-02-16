package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class PrototypeFour {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        GizmoBallView gui = new GizmoBallView(model);
        JFrame frame = gui.getGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

