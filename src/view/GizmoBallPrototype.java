package src.view;

import src.controller.FlipKeyListener;
import src.model.Model;
import src.model.FlipperModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.Timer;

public class GizmoBallPrototype extends JPanel {
    private FlipperModel flipper;
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
        flipper = new FlipperModel(false);
        flipperView = new FlipperView();

        // TODO: work out how to get the key events forwarded to the src.view.GizmoBallPrototype properly
        window.addKeyListener(new FlipKeyListener(flipper));

        new Timer(1000 / Model.TICKS_PER_SECOND, e -> {
            flipper.tick();
            repaint();
        }).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.translate(64, 64);
        // draw flipper
        flipperView.paint(g2, flipper);
    }
}
