package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.model.Ball;
import gizmoball.model.gizmos.*;

public class PrototypeThree extends Main {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        try {
            model.select(1.5, 1);
            model.addBall(new Ball());
            model.select(1, 10);
            model.addGizmo(new Triangle());
            model.rotateGizmo();
            model.rotateGizmo();
            model.rotateGizmo();
            model.select(10, 13);
            model.addGizmo(new Circle());
            model.select(5, 18);
            model.addGizmo(new Square());
            model.select(10, 18);
            model.addGizmo(new Square());
            model.select(15, 18);
            model.addGizmo(new Square());
        } catch (Exception e) {
            e.printStackTrace();
        }
        openGUI(model);
    }
}

