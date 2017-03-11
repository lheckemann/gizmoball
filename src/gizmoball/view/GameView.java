package gizmoball.view;

import javax.swing.*;

public abstract class GameView {
    protected final int panelWidth = 200;
    protected Box box;

    public Box getBox() {
        return this.box;
    }

    public abstract void updateBoard();
}
