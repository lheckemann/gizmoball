package gizmoball.main;

import java.io.*;

import gizmoball.model.Model;
import gizmoball.model.SyntaxError;
import gizmoball.controller.load.StandardLoader;
import gizmoball.controller.Controller;
import gizmoball.view.GizmoBallView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model(20, 20);
        if (args.length == 1) {
            try {
                model.load(new StandardLoader(), new FileInputStream(args[0]));
            } catch (FileNotFoundException | SyntaxError e) {
                System.out.println(e.getMessage());
            }
        } else if (args.length > 1) {
            System.out.println("Usage: [FILENAME]");
            System.exit(1);
        }
        Main.openGUI(model);
    }

    public static void openGUI(Model model) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GizmoBallView gui = new GizmoBallView(model, new Controller());
                JFrame frame = gui.getGUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

