
package gizmoball.main;

import gizmoball.model.gizmos.*;
import gizmoball.model.Model;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

import static java.awt.event.KeyEvent.VK_SPACE;

public class PrototypeOne {
    public static void main(String[] args) {
        Model model = new Model(16, 16);
        int x = 1;
        for (Rotation rot : Rotation.values()) {
            model.select(x, 2);
            try {
				model.addRightFlipper();
			} catch (PositionOverlapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            for (int i = 0; i < rot.getTurns(); i++) {
                model.rotateGizmo();
            }
            model.triggerOnKeyPress(VK_SPACE);
            model.triggerOnKeyRelease(VK_SPACE);
            x += 3;
        }

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

