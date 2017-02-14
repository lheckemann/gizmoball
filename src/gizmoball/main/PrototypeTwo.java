package gizmoball.main;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

/**
 * Created by fra_m on 14/02/2017.
 */
public class PrototypeTwo
{
    public static void main(String[] args) {
        BuildModel model = new Model(20, 20);

        GizmoBallView gui = new GizmoBallView(model);
        JFrame frame = gui.getGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
