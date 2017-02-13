package gizmoball.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import java.awt.*;

import gizmoball.model.Model;
import gizmoball.model.BuildModel;
import gizmoball.model.SyntaxError;
import gizmoball.view.BuildView;


public class PrototypeFour {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Supply a filename.");
            System.exit(1);
        }
        JFrame window = new JFrame();
        BuildModel model = new Model(20, 20);
        try {
            model.load(new FileInputStream(args[0]));
        } catch (FileNotFoundException|SyntaxError e) {
            System.out.println(e);
            System.exit(1);
        }
        window.add(new BuildView(model).getComponent());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(300, 300));
    }
}
