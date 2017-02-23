package gizmoball.view;

import javax.swing.*;

public class GameView {
    protected BoardView board;
    protected Box box;

    protected final int panelWidth = 200;

    public Box getBox() {
        return this.box;
    }

    public void updateBoard() {
        this.board.updateUI();
    }
}
