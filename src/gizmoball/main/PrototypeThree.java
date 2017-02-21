package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.view.GizmoBallView;

public class PrototypeThree extends Main {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        try {
            model.select(1.5, 1);
            model.addBall(0.0, 0.0);
            model.select(1, 10);
            model.addTriangle();
            model.rotateGizmo();
            model.rotateGizmo();
            model.rotateGizmo();
            model.select(10, 13);
            model.addCircle();
            model.select(5, 18);
            model.addSquare();
            model.select(10, 18);
            model.addSquare();
            model.select(15, 18);
            model.addSquare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        openGUI(model);
    }
}

