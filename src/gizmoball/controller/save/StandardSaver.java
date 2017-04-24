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

import java.util.stream.Collectors;

import gizmoball.model.BuildModel;

public class StandardSaver extends Saver {
    public StandardSaver(BuildModel model) {
        super(model);
        this.taggedGizmos = this.getTaggedGizmos(model.getGizmos()
                .stream()
                .filter(g -> g.getType().isSpecified())
                .collect(Collectors.toList()));
    }
}
