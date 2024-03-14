package pepse.world.daynight;

import java.awt.Color;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents the halo around the sun.
 */
public class SunHalo {
    private final static Color SUNHALO = new Color(255, 255, 0, 20);
    private final static int HALOSIZE = 175;
    private final static String SUNHALOTAG = "sunhalo";

    /**
     * Creates a GameObject representing the halo around the sun.
     * 
     * @param sun The GameObject representing the sun.
     * @return A GameObject representing the halo around the sun.
     */
    public static GameObject create(GameObject sun){
        Renderable renderable = new OvalRenderable(SUNHALO);
        GameObject sunHalo = new GameObject(sun.getTopLeftCorner(), Vector2.ONES.mult(HALOSIZE), renderable);
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag(SUNHALOTAG);
        return sunHalo;
    }
}
