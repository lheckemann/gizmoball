package gizmoball.view;

import javax.swing.*;

public class GameView {
    protected BoardView board;
    protected Box box;

    public Box getBox() {
        return this.box;
    }

    public void updateBoard() {
    	this.board.repaint();
    }
}
