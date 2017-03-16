package gizmoball.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import gizmoball.controller.*;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.GizmoType;

import javax.swing.*;

public class BuildBoardView extends BoardView implements IBuildBoardView {
    private Controller controller;

    public BuildBoardView(BuildModel model, Controller controller) {
        super(model);
        this.controller = controller;

        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.lightGray);
        for (int x = 0; x <= getModel().getWidth() * BoardView.L_TO_PIXELS; x += BoardView.L_TO_PIXELS)
            g.drawLine(x, 0, x, getHeight());
        for (int y = 0; y <= getModel().getHeight() * BoardView.L_TO_PIXELS; y += BoardView.L_TO_PIXELS)
            g.drawLine(0, y, getWidth(), y);

        super.paintGizmos(g);
    }

    private void clearListeners() {
        for (MouseListener m: this.getMouseListeners()) {
            this.removeMouseListener(m);
        }

        for (MouseMotionListener m: this.getMouseMotionListeners()) {
            this.removeMouseMotionListener(m);
        }

        for (KeyListener k: this.getKeyListeners()) {
            this.removeKeyListener(k);
        }
    }

    @Override
    public void switchToAddGizmo(GizmoType type, IBuildView view, BuildModel model) {
        this.clearListeners();
        EventListener createGizmoListener = controller.getCreateGizmoListener(type, view, model);
        this.addMouseListener((MouseListener) createGizmoListener);
        this.addMouseMotionListener((MouseMotionListener) createGizmoListener);
    }

    @Override
    public void switchToRotate(IBuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(controller.getRotateActionListener(view, model));
    }

    @Override
    public void switchToDelete(IBuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(controller.getDeleteGizmoListener(view, model));
    }

    @Override
    public void switchToMove(IBuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(controller.getMoveGizmoListener(view, model));
    }

    @Override
    public void switchToConnectGizmos(IBuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(controller.getConnectGizmosListener(view, model));
    }

    @Override
    public void switchToConnectKeyPressGizmo(IBuildView view, BuildModel model) {
        this.clearListeners();
        EventListener keyPressGizmoListener = controller.getConnectKeyPressGizmoListener(view, model);
        this.addKeyListener((KeyListener) keyPressGizmoListener);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener((MouseListener) keyPressGizmoListener);
    }

    @Override
    public void switchToConnectKeyReleaseGizmo(IBuildView view, BuildModel model) {
        this.clearListeners();
        EventListener keyReleaseGizmoListener = controller.getConnectKeyReleaseGizmoListener(view, model);
        this.addKeyListener((KeyListener) keyReleaseGizmoListener);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener((MouseListener)keyReleaseGizmoListener);
    }
    
    @Override 
    public void switchToConnectOuterwall(IBuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(controller.getConnectOuterwallListener(view, model));
    }

    @Override
    public void switchToAddBall(IBuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(controller.getAddBallListener(view, model));
    }
}
