package gizmoball.view;
import java.awt.*;

import gizmoball.model.BuildModel;

import javax.swing.*;

public class BuildBoardView extends BoardView {
    public BuildBoardView(BuildModel model) {
        super(model);

        this.setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for (int x = 0; x <= getModel().getWidth() * BoardView.L_TO_PIXELS; x += BoardView.L_TO_PIXELS)
            g.drawLine(x, 0, x, getHeight());
        for (int y = 0; y <= getModel().getHeight() * BoardView.L_TO_PIXELS; y += BoardView.L_TO_PIXELS)
            g.drawLine(0, y, getWidth(), y);

        super.paintGizmos(g);
    }
}
