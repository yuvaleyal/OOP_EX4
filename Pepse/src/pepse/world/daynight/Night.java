package pepse.world.daynight;

import java.awt.Color;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.components.Transition.TransitionType;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;

/**
 * Represents the night environment in the game.
 */
public class Night {
    private static final String NIGHT_TAG = "night";
    private static final Float MIDNIGHT_OPACITY = 0.5f;

    
    /**
     * Creates a GameObject representing the night environment.
     * 
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength The length of the cycle for opacity transition.
     * @return A GameObject representing the night environment.
     */
    public static GameObject create(Vector2 windowDimensions,float cycleLength){
        Renderable renderable = new RectangleRenderable(Color.BLACK);
        GameObject night = new GameObject(Vector2.ZERO, windowDimensions, renderable);
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag(NIGHT_TAG);
        new Transition<Float>(night, night.renderer()::setOpaqueness
        , 0.0f, MIDNIGHT_OPACITY, Transition.CUBIC_INTERPOLATOR_FLOAT, cycleLength,
         TransitionType.TRANSITION_BACK_AND_FORTH, null);
        return night;
    }

}
