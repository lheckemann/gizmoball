package gizmoball.main;

import gizmoball.model.Model;
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
				} catch (PositionOverlapException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	model.move(4, 4);
            	try {
					model.addAbsorber(19, 2);
				} catch (PositionOverlapException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
