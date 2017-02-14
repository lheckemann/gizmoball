package gizmoball.view;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel
{
    public BoardView() {
        this.setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public Dimension getPreferredSize() {
        return new Dimension(20 * 32, 20 * 32);
    }
}