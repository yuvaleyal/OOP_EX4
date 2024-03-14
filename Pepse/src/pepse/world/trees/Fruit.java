package pepse.world.trees;

import java.awt.Color;

import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.world.Block;

public class Fruit extends Block {
    private static final Color FRUIT_COLOR = new Color(0, 0, 0);

    public Fruit(Vector2 topLeftCorner) {
        super(topLeftCorner, new OvalRenderable(FRUIT_COLOR));
    }

}
