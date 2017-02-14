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
/*
public class RunBoardView extends JComponent {
    private final RunModel model;
    private final FlipperView flipperView = new FlipperView();

    public RunBoardView(RunModel model) {
        this.model = model;
        addKeyListener(new KeyTriggerListener(model));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.translate(getX(), getY());
        AffineTransform origin = g.getTransform();
        for (ReadGizmo gizmo : model.getGizmos()) {
            g.translate(gizmo.getX() * Model.L_TO_PIXELS, gizmo.getY() * Model.L_TO_PIXELS);
            g.translate(gizmo.getWidth() / 2.0, gizmo.getHeight() / 2.0);
            g.rotate(gizmo.getRotation().getRadiansFromNorth());
            g.translate(gizmo.getWidth() / -2.0, gizmo.getHeight() / -2.0);
            switch (gizmo.getType()) {
                case LEFT_FLIPPER:
                case RIGHT_FLIPPER:
                    flipperView.paint(g, gizmo);
            }
            g.setTransform(origin);
        }
    }
}
*/