package gizmoball.view;

import gizmoball.model.IModel;
import gizmoball.model.Model;

import java.awt.*;

public class RunBoardView extends BoardView {
    public RunBoardView(IModel model) {
        super(model);
    }

    @Override
    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintGizmos(g);
    }*/
    
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
