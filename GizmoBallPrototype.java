import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class GizmoBallPrototype extends JPanel {
    private FlipperModel flipper;
    private FlipperView flipperView;
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.add(new GizmoBallPrototype());
        // window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GizmoBallPrototype() {
        flipper = new FlipperModel(false);
        flipperView = new FlipperView();

        new Timer(1000/Model.TICKS_PER_SECOND, e -> {flipper.tick(); repaint();}).start();

        this.addKeyListener(
            new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == e.VK_SPACE) {
                        flipper.trigger();
                    }
                }
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == e.VK_SPACE) {
                        flipper.untrigger();
                    }
                }
            }
        );
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // draw flipper
        flipperView.paint(g2, flipper);
    }
}
