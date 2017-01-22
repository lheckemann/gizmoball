package src.view;

import src.model.Model;
import src.model.FlipperModel;

import java.awt.Color;
import java.awt.Graphics2D;

public class FlipperView {
    public void paint(Graphics2D graphics, FlipperModel flipper) {
        graphics.setColor(Color.ORANGE);
        int x = 0,
                y = 0,
                width = 0,
                height = 0;
        graphics.rotate(Math.toRadians(flipper.getPosition()));
        if (!flipper.isLeft()) {
            graphics.scale(-1, 1);
        }
        switch (flipper.getRotation()) {
            case NORTH:
                x = 0;
                y = 0;
                width = Model.L_TO_PIXELS / 2;
                height = Model.L_TO_PIXELS * 2;
                break;

            case EAST:
                x = 0;
                y = 0;
                width = Model.L_TO_PIXELS * 2;
                height = Model.L_TO_PIXELS / 2;
                break;

            case SOUTH:
                x = Model.L_TO_PIXELS * 3 / 2;
                y = 0;
                width = Model.L_TO_PIXELS / 2;
                height = Model.L_TO_PIXELS * 2;
                break;

            case WEST:
                x = 0;
                y = Model.L_TO_PIXELS * 3 / 2;
                width = Model.L_TO_PIXELS * 2;
                height = Model.L_TO_PIXELS / 2;
                break;
        }

        graphics.fillRect(x, y, width, height);
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
