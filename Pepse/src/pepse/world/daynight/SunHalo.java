package pepse.world.daynight;

import java.awt.Color;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class SunHalo {
    private final static Color SUNHALO = new Color(255, 255, 0, 20);
    private final static int HALOSIZE = 175;
    private final static String SUNHALOTAG = "sunhalo";
    public static GameObject create(GameObject sun){
        Renderable renderable = new OvalRenderable(SUNHALO);
        GameObject sunHalo = new GameObject(sun.getTopLeftCorner(), Vector2.ONES.mult(HALOSIZE), renderable);
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag(SUNHALOTAG);
        return sunHalo;
    }
}
