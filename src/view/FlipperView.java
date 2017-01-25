package view;

import model.Model;
import model.FlipperModel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class FlipperView {
    public void paint(Graphics2D graphics, FlipperModel flipper) {
        graphics.setColor(Color.ORANGE);
        AffineTransform saved = graphics.getTransform();

        graphics.translate(Model.L_TO_PIXELS, Model.L_TO_PIXELS);
        if (!flipper.isLeft()) {
            graphics.scale(-1, 1);
        }
        switch (flipper.getRotation()) {
            case NORTH:
                break;

            case EAST:
                graphics.rotate(Math.toRadians(90));
                break;

            case SOUTH:
                graphics.rotate(Math.toRadians(180));
                break;

            case WEST:
                graphics.rotate(Math.toRadians(270));
                break;
        }
        graphics.translate(-Model.L_TO_PIXELS, -Model.L_TO_PIXELS);

        graphics.translate(Model.L_TO_PIXELS * 0.25, Model.L_TO_PIXELS * 0.25);
        graphics.rotate(-Math.toRadians(flipper.getPosition()));
        graphics.translate(-Model.L_TO_PIXELS * 0.25, -Model.L_TO_PIXELS * 0.25);

        graphics.fillRoundRect(0, 0, Model.L_TO_PIXELS / 2, Model.L_TO_PIXELS * 2, Model.L_TO_PIXELS / 2, Model.L_TO_PIXELS / 2);
        graphics.setColor(Color.RED);
        graphics.fillOval(Model.L_TO_PIXELS / 8, Model.L_TO_PIXELS / 8, Model.L_TO_PIXELS / 4, Model.L_TO_PIXELS / 4);
        graphics.setTransform(saved);
    }
}


/*
 *
 * North left
 * |.       \.          --
 * |.  ->   .\    ->    ..
 *
 * East left:
 * --        ./         .|
 * ..  ->    /.    ->   .|
 *
 * South left
 * .|       \.          ..
 * .|   ->  .\     ->   --
 *
 *
 * West left
 * ..         ./        |.
 * --    ->   /.   ->   |.
 *
 * North right
 * .|       ./          --
 * .|   ->  /.     ->   ..
 *
 * East right
 * ..       \.          .|
 * __   ->  .\     ->   .|
 *
 * South right
 * |.       ./          ..
 * |.   ->  /      ->   --
 *
 * West right
 * --       \.          |.
 * ..   ->  .\     ->   |.
 */
