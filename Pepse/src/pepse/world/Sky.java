package pepse.world;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import java.awt.Color;

/**
 * Represents the sky in the game.
 */
public class Sky {
    private static final Color BASIC_SKY_COLOR = Color.decode("#80c6e5");
    private static final String SKY_TAG = "sky";

    private Sky() {
    }

    /**
     * Creates a sky game object with the specified window dimensions.
     * 
     * @param windowDimensions The dimensions of the game window.
     * @return The created sky game object.
     */
    public static GameObject create(Vector2 windowDimentions) {
        GameObject sky = new GameObject(Vector2.ZERO, windowDimentions,
                new RectangleRenderable(BASIC_SKY_COLOR));
        sky.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sky.setTag(SKY_TAG);
        return sky;
    }
}
