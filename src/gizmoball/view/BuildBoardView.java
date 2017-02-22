package gizmoball.view;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gizmoball.controller.AddBallListener;
import gizmoball.controller.ConnectGizmosListener;
import gizmoball.controller.ConnectKeyPressGizmoListener;
import gizmoball.controller.ConnectKeyReleaseGizmoListener;
import gizmoball.controller.CreateGizmoListener;
import gizmoball.controller.DeleteGizmoListener;
import gizmoball.controller.MoveGizmoListener;
import gizmoball.controller.RotateGizmoListener;
import gizmoball.model.BuildModel;
import gizmoball.model.gizmos.ReadGizmo.GizmoType;

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
    
    public void switchToAddGizmo(GizmoType type, BuildView view, BuildModel model) {
        this.clearListeners();
        CreateGizmoListener createGizmoListener = new CreateGizmoListener(type, view, model);
        this.addMouseListener(createGizmoListener);
        this.addMouseMotionListener(createGizmoListener);
    }
    
    public void switchToRotate(BuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(new RotateGizmoListener(view, model));
    }
    
    public void switchToDelete(BuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(new DeleteGizmoListener(view, model));
    }
    
    public void switchToMove(BuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(new MoveGizmoListener(view, model));
    }

    public void switchToConnectGizmos(BuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(new ConnectGizmosListener(view, model));
    }

    public void switchToConnectKeyPressGizmo(BuildModel model) {
        this.clearListeners();
        ConnectKeyPressGizmoListener keyPressGizmoListener = new ConnectKeyPressGizmoListener(model);
        this.addKeyListener(keyPressGizmoListener);
        this.addMouseListener(keyPressGizmoListener);
    }
    
    public void switchToConnectKeyReleaseGizmo(BuildModel model) {
        this.clearListeners();
        ConnectKeyReleaseGizmoListener keyReleaseGizmoListener = new ConnectKeyReleaseGizmoListener(model);
        this.addKeyListener((KeyAdapter)keyReleaseGizmoListener);
        this.addMouseListener((MouseListener)keyReleaseGizmoListener);
        this.requestFocusInWindow();
    }

    public void switchToAddBall(BuildView view, BuildModel model) {
        this.clearListeners();
        this.addMouseListener(new AddBallListener(view, model));
    }
   
}
