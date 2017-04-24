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

import gizmoball.model.Model;
import gizmoball.model.SyntaxError;
import gizmoball.controller.load.StandardLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Validate {
    public static void main(String[] args) throws FileNotFoundException, SyntaxError {
        Model model = new Model(20, 20);
        for (String filename : args) {
            File file = new File(filename);
            System.err.println("Checking " + file);
            model.load(new StandardLoader(), new FileInputStream(file));
        }
    }
}
