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
package gizmoball.model;

public class SyntaxError extends Exception {
    public static final long serialVersionUID = 1L;
    private final Integer lineNumber;
    private final String line;
    private String message;

    public SyntaxError(Integer lineNumber, String line) {
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public SyntaxError(Integer lineNumber, String line, String message) {
        this.lineNumber = lineNumber;
        this.line = line;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format("Line %d. (\"%s\"): %s", this.lineNumber, this.line, this.message);
    }
}
