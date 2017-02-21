package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.model.PositionOutOfBoundsException;
import gizmoball.model.PositionOverlapException;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class PrototypeTwo extends Main {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        try {
            model.setGravity(40);
            model.select(0, 19);
            model.addAbsorber(20, 1);
            model.triggerOnKeyRelease(32);
            model.select(1, 1);
            model.addBall(0.0, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        openGUI(model);
    }
}
