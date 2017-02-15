package gizmoball.view;

import gizmoball.model.IModel;

import java.awt.*;

public class RunBoardView extends BoardView {
    public RunBoardView(IModel model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
