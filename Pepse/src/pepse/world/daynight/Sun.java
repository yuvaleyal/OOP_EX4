package pepse.world.daynight;

import java.awt.Color;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.components.Transition.TransitionType;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Sun {
    private static final int SUN_SIZE = 100;
    private static final String SUN_TAG = "sun";
    public static GameObject create(Vector2 windowDimensions, float cycleLength){
        Renderable renderable = new OvalRenderable(Color.YELLOW);
        Vector2 starting_place = new Vector2(windowDimensions.x()/2, windowDimensions.y()/3);
        GameObject sun = new GameObject(starting_place, Vector2.ONES.mult(SUN_SIZE), renderable);
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag(SUN_TAG);
        Vector2 initialSunCenter = sun.getCenter();
        Vector2 cycleCenter = new Vector2(windowDimensions.x()/2, windowDimensions.y()*2/3);
        new Transition<Float>(sun, (Float angle) -> sun.setCenter(initialSunCenter.subtract(cycleCenter)
        .rotated(angle).add(cycleCenter)), 0f,360f, Transition.LINEAR_INTERPOLATOR_FLOAT,cycleLength
        , TransitionType.TRANSITION_LOOP,null);
        return sun;
    }
}
