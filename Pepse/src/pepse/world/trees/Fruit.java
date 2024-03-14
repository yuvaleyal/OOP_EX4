package pepse.world.trees;

import java.awt.Color;

import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.world.Block;

/**
 * Represents a fruit block in the game.
 */
public class Fruit extends Block {
    private static final Color FRUIT_COLOR = new Color(0, 0, 0);

    /**
     * Constructs a Fruit object with the specified top-left corner position.
     * 
     * @param topLeftCorner The top-left corner position of the fruit block.
     */
    public Fruit(Vector2 topLeftCorner) {
        super(topLeftCorner, new OvalRenderable(FRUIT_COLOR));
    }

}
