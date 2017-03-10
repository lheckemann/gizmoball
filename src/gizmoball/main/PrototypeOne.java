package gizmoball.main;

import gizmoball.model.Model;

public class PrototypeOne extends Main {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        try {
            for (int i = 0; i < 20; i += 4) {
                model.select(i, i);
                model.addRightFlipper();
                model.triggerOnKeyPress(32);
                model.triggerOnKeyRelease(32);
                model.select(i+2, i+2);
                model.addLeftFlipper();
                model.triggerOnKeyPress(32);
                model.triggerOnKeyRelease(32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        openGUI(model);
    }
}

