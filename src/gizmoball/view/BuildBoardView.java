package gizmoball.view;
import java.awt.*;

import gizmoball.model.IModel;
import gizmoball.model.Model;

public class BuildBoardView extends BoardView {
    public BuildBoardView(IModel model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for (int x = 0; x <= 20 * Model.L_TO_PIXELS; x += Model.L_TO_PIXELS)
            g.drawLine(x, 0, x, getHeight());
        for (int y = 0; y <= 20 * Model.L_TO_PIXELS; y += Model.L_TO_PIXELS)
            g.drawLine(0, y, getWidth(), y);
        
        super.paintGizmos(g);
    }
}
