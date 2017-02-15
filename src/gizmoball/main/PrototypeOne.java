
package gizmoball.main;

import gizmoball.model.gizmos.*;
import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class PrototypeOne {
    public static void main(String[] args) {
        Model model = new Model(16, 16);
        int x = 1;
        for (Rotation rot : Rotation.values()) {
            model.select(x, 2);
            model.addRightFlipper("F" + x);
            model.rotateGizmo();
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

