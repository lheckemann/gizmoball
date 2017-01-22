import java.awt.Color;
import javax.swing.Graphics2D;
public class FlipperView {
	public void paint(Graphics2D graphics, FlipperModel flipper) {
		graphics.setColor(Color.ORANGE);
		int x, y, width, height;
		graphics.rotate(Math.toRadians(flipper.getPosition()));
		if (!flipper.isLeft()) {
			graphics.scale(-1, 1);
		}
		switch (flipper.getRotation()) {
			case Rotation.NORTH:
				x = 0;
				y = 0;
				width = 0.5 * L_TO_PIXELS;
				height = 2 * L_TO_PIXELS;
				break;

			case Rotation.EAST:
				x = 0;
				y = 0;
				width = 2 * L_TO_PIXELS;
				height = 0.5 * L_TO_PIXELS;
				break;

			case Rotation.SOUTH:
				x = 1.5 * L_TO_PIXELS;
				y = 0;
				width = 0.5 * L_TO_PIXELS;
				height = 2 * L_TO_PIXELS;
				break;

			case Rotation.WEST:
				x = 0;
				y = 1.5 * L_TO_PIXELS;
				width = 2 * L_TO_PIXELS;
				height = 0.5 * L_TO_PIXELS;
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
