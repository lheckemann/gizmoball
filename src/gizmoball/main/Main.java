/*
(C) 2017 Linus Heckemann, William Macdonald, Francesco Meggetto, Unai Zalakain

This file is part of Gizmoball.

Gizmoball is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gizmoball is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gizmoball.  If not, see <http://www.gnu.org/licenses/>.
*/
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

