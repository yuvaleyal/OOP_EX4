package pepse.world.trees;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.Transition;
import danogl.components.Transition.TransitionType;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.world.Block;

public class Leaves {
    private static final Color BASIC_COLOR = new Color(50, 200, 30);
    private static final Random random = new Random();
    private static final float FINAL_ROTATING_ANGLE = 360f;
    private static final float TRANSITION_LENGTH = 2;
    private static final float MIN_WIDTH = 10f;
    private static final float MAX_WIDTH = 60f;
    private List<Block> leaves;

    /**
     * 
     * @param numOfLeaves: expects a non-even number
     * @param bottomMiddle
     */
    public Leaves(int numOfLeaves, Vector2 bottomMiddle) {
        leaves = new ArrayList<Block>(numOfLeaves * numOfLeaves);
        float initialX = bottomMiddle.x() - ((numOfLeaves - 1) / 2 * Block.getSize());
        float y = bottomMiddle.y() - (numOfLeaves * Block.getSize());
        float x;
        for (int i = 0; i < numOfLeaves; i++) {
            x = initialX;
            for (int j = 0; j < numOfLeaves; j++) {
                if (random.nextDouble() < 0.6) {
                    leaves.add(new Block(new Vector2(x, y),
                            new RectangleRenderable(BASIC_COLOR)));
                }
                x += Block.getSize();
            }
            y += Block.getSize();
        }
        setTransitions();
    }

    public void addLeaves(GameObjectCollection collection) {
        for (Block block : leaves) {
            collection.addGameObject(block, Layer.FOREGROUND);
        }
    }

    private void setTransitions() {
        for (Block leaf : leaves) {
            float leafHeight = leaf.getDimensions().y();
            new Transition<Float>(leaf,
                    (Float angle) -> leaf.renderer().setRenderableAngle(angle), 0f,
                    FINAL_ROTATING_ANGLE, Transition.LINEAR_INTERPOLATOR_FLOAT,
                    TRANSITION_LENGTH, TransitionType.TRANSITION_BACK_AND_FORTH, null);
            new Transition<Float>(leaf,
                    (Float width) -> leaf.setDimensions(new Vector2(width, leafHeight)),
                    MIN_WIDTH, MAX_WIDTH, Transition.LINEAR_INTERPOLATOR_FLOAT,
                    TRANSITION_LENGTH, TransitionType.TRANSITION_BACK_AND_FORTH, null);
        }
    }
}
