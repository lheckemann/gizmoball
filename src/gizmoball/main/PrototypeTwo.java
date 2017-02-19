package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class PrototypeTwo {
    public static void main(String[] args) {
        Model model = new Model(20, 20);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addBall(0.0, 0.0);
                    model.move(4, 4);
                    model.addAbsorber(19, 2);
                    model.move(0, 18);
                } catch (PositionOverlapException e1) {
                    e1.printStackTrace();
                } catch (PositionOutOfBoundsException e) {
                    e.printStackTrace();
                }
                model.triggerOnKeyPress(32);
                GizmoBallView gui = new GizmoBallView(model);
                JFrame frame = gui.getGUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
