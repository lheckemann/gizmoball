package gizmoball.main;

import gizmoball.model.Model;
import gizmoball.model.SyntaxError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Validate {
    public static void main(String[] args) throws FileNotFoundException, SyntaxError {
        Model model = new Model(20, 20);
        for (String filename : args) {
            File file = new File(filename);
            System.err.println("Checking " + file);
            model.load(new FileInputStream(file));
        }
    }
}
