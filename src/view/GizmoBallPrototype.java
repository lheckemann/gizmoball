package src.view;

import src.model.Model;
import src.model.FlipperModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class GizmoBallPrototype extends JPanel {
    private FlipperModel flipper;
    private FlipperView flipperView;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        GizmoBallPrototype p = new GizmoBallPrototype();
        window.add(p);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(300, 300));

        // TODO: work out how to get the key events forwarded to the src.view.GizmoBallPrototype properly
        window.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == e.VK_SPACE) {
                            p.flipper.trigger();
                        }
                    }

                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == e.VK_SPACE) {
                            p.flipper.untrigger();
                        }
                    }
                }
        );
    }

    public GizmoBallPrototype() {
        flipper = new FlipperModel(false);
        flipperView = new FlipperView();

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
