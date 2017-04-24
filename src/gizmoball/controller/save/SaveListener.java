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
package gizmoball.controller.save;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import gizmoball.model.Model;
import gizmoball.view.IGizmoBallView;

public class SaveListener implements ActionListener {
    private Model model;
    private final IGizmoBallView view;

    public SaveListener(Model model, IGizmoBallView view) {
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Saver saver = view.promptSaverType(this.model);
        if(saver != null) {
        	try {
                File file = view.getFileByChooserSave();
                if(file != null) {
                    saver.save(new FileOutputStream(file));
                }
            } catch (FileNotFoundException fnfe) {
                view.displayErrorMessage(fnfe.getMessage(), "File not found");
            }
        }
    }
}
