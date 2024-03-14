package pepse.world.trees;

import java.awt.Color;
import java.util.Random;

import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.components.Transition.TransitionType;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.world.Block;

/**
 * Represents a leaf object in the game.
 */
public class Leaf extends Block {
    private static final Color BASIC_COLOR = new Color(50, 200, 30);
    private static final float FINAL_ROTATING_ANGLE = 45f;
    private static final float TRANSITION_LENGTH = 2;
    private static final float MIN_WIDTH = 10f;
    private static final float MAX_WIDTH = 60f;
    private static final Random random = new Random();

    /**
     * Constructs a leaf object with the specified top-left corner position.
     * 
     * @param topLeftCorner The top-left corner position of the leaf.
     */
    public Leaf(Vector2 topLeftCorner) {
        super(topLeftCorner, new RectangleRenderable(BASIC_COLOR));
    }

    /**
     * Adds a transition effect to the leaf object.
     */
    public void addTransition() {
        new ScheduledTask(this, (float) random.nextDouble(), false,
                () -> setTransition());
    }

    private void setTransition() {
        float leafHeight = getSize();
        new Transition<Float>(this,
                (Float angle) -> this.renderer().setRenderableAngle(angle), 0f,
                FINAL_ROTATING_ANGLE, Transition.LINEAR_INTERPOLATOR_FLOAT,
                TRANSITION_LENGTH, TransitionType.TRANSITION_BACK_AND_FORTH, null);
        new Transition<Float>(this,
                (Float width) -> this.setDimensions(new Vector2(width, leafHeight)),
                MIN_WIDTH, MAX_WIDTH, Transition.LINEAR_INTERPOLATOR_FLOAT,
                TRANSITION_LENGTH, TransitionType.TRANSITION_BACK_AND_FORTH, null);
    }
}
