package gizmoball.view;

import gizmoball.model.BuildModel;
import gizmoball.model.IModel;
import gizmoball.model.gizmos.ReadGizmo;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class BoardView extends JPanel implements Observer
{
    private IModel model;

    public BoardView(IModel model)
    {
        this.model = model;
        this.model.addObserver(this);

        this.setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public Dimension getPreferredSize() {
        return new Dimension(20 * 32, 20 * 32);
    }

    public void drawLeftFlipper() {

    }

    public void drawRightFlipper() {

    }

    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("update works");
        Collection<ReadGizmo> giz = model.getGizmos();
        for(ReadGizmo g : giz)
        {
            switch(g.getType()) {
                case ABSORBER:

                    break;
                case SQUARE:
                    break;
                case CIRCLE:
                    break;
                case LEFT_FLIPPER:

                    break;
                case RIGHT_FLIPPER:
                    break;
                case TRIANGLE:
                    break;
            }
        }
    }
}