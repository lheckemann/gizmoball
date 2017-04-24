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
package gizmoball.controller.load;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import gizmoball.model.Model;
import gizmoball.model.SyntaxError;
import gizmoball.view.IGizmoBallView;

public class LoadListener implements ActionListener {
    private final Model model;
    private final IGizmoBallView view;

    public LoadListener(Model model, IGizmoBallView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File loadedFile = view.getFileByChooserLoad();
            if(loadedFile != null) {
                model.load(new StandardLoader(), new FileInputStream(loadedFile));
            }
            view.updateBoard();
        } catch (FileNotFoundException fnfe) {
            view.displayErrorMessage(fnfe.getMessage(), "File not found");
        } catch (SyntaxError err) {
            view.displayErrorMessage(err.getMessage(), "Syntax error in file");
        }
    }
}
