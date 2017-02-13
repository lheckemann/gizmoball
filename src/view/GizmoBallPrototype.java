package view;

import model.Model;
import model.Flipper;
import model.RunModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.Timer;

public class GizmoBallPrototype {
    private Flipper[] flippers;
    private FlipperView flipperView;
    private static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        RunModel model = Model.prototype1Example();
        window.add(new RunView(model).getComponent());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(300, 300));
    }

    /*
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
        for (Flipper flipper : flippers) {
            g2.translate(96, 0);
            flipperView.paint(g2, flipper);
        }
    }
    */
}
