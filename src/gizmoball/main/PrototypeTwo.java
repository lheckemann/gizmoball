package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class PrototypeTwo {
    public static void main(String[] args) {
        Model model = new Model(20, 20);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	model.addBall("B0");
            	model.move(4, 4);
            	model.addAbsorber("A0", 19, 2);
            	model.move(0, 18);
            	model.triggerOnKeyPress(32);
                GizmoBallView gui = new GizmoBallView(model);
                JFrame frame = gui.getGUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
