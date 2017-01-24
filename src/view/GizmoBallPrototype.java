package view;

import controller.FlipKeyListener;
import model.Model;
import model.FlipperModel;
import model.Rotation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.Timer;

public class GizmoBallPrototype extends JPanel {
    private FlipperModel[] flippers;
    private FlipperView flipperView;
    private static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        GizmoBallPrototype p = new GizmoBallPrototype();
        window.add(p);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(300, 300));
    }

    public GizmoBallPrototype() {
        flippers = new FlipperModel[8];
        int i = 0;
        for (Rotation rot : Rotation.values()) {
            flippers[i] = new FlipperModel(true);
            flippers[i].setRotation(rot);
            flippers[i+4] = new FlipperModel(false);
            flippers[i+4].setRotation(rot);
            window.addKeyListener(new FlipKeyListener(flippers[i]));
            window.addKeyListener(new FlipKeyListener(flippers[i+4]));
            i++;
        }
        flipperView = new FlipperView();


        new Timer(1000 / Model.TICKS_PER_SECOND, e -> {
            for (FlipperModel flipper : flippers) {
                flipper.tick();
            }
            // TODO this might be the wrong way to do it but at least it's smooth
            updateUI();
        }).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.gray);
        for (int x = 0; x < 30 * Model.L_TO_PIXELS; x += Model.L_TO_PIXELS)
            g2.drawLine(x, 0, x, 500);
        for (int y = 0; y < 30 * Model.L_TO_PIXELS; y += Model.L_TO_PIXELS)
            g2.drawLine(0, y, 1000, y);
        g2.translate(32, 32);
        // draw flipper
        for (FlipperModel flipper : flippers) {
            g2.translate(96, 0);
            flipperView.paint(g2, flipper);
        }
    }
}
