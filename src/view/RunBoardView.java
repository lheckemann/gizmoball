package view;

import controller.KeyTriggerListener;
import controller.TickListener;
import model.Model;
import model.ReadGizmo;
import model.ReadModel;
import model.RunModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

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
            g.rotate(gizmo.getRotation().radiansFromNorth());
            g.translate(gizmo.getWidth() / -2.0, gizmo.getHeight() / -2.0);
            switch (gizmo.getType()) {
                case LEFT_FLIPPER:
                case RIGHT_FLIPPER:
                    flipperView.paint(g, gizmo);
            }
        }
        g.setTransform(origin);
    }
}
