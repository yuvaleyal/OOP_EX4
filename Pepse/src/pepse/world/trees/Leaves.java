package pepse.world.trees;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.components.Transition.TransitionType;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

/**
 * Represents a collection of leaves in the game.
 */
public class Leaves {
    private static final Random random = new Random();
    private static final double SPAWNING_PROBABILITY = 0.6;
    private static final int LEAVES_LAYER = -50;
    private List<Leaf> leaves;

    /**
     * Constructs a collection of leaves with the specified number of leaves and
     * bottom middle position.
     * 
     * @param numOfLeaves  The number of leaves.
     * @param bottomMiddle The bottom middle position.
     */
    public Leaves(int numOfLeaves, Vector2 bottomMiddle) {
        int leafSize = Leaf.getSize();
        leaves = new ArrayList<Leaf>(numOfLeaves * numOfLeaves);
        float initialX = bottomMiddle.x() - ((numOfLeaves - 1) / 2 * leafSize);
        float y = bottomMiddle.y() - (numOfLeaves * leafSize);
        float x;
        for (int i = 0; i < numOfLeaves; i++) {
            x = initialX;
            for (int j = 0; j < numOfLeaves; j++) {
                if (random.nextDouble() < SPAWNING_PROBABILITY) {
                    leaves.add(new Leaf(new Vector2(x, y)));
                }
                x += leafSize;
            }
            y += leafSize;
        }
        setTransitions();
    }

    /**
     * Adds the leaves to the specified game object collection.
     * 
     * @param collection The game object collection to add the leaves to.
     */
    public void addLeaves(GameObjectCollection collection) {
        for (Leaf leaf : leaves) {
            collection.addGameObject(leaf, LEAVES_LAYER);
        }
    }

    public void rotate90Degrees() {
        for (Leaf leaf : leaves) {
            float curAngle = leaf.renderer().getRenderableAngle();
            leaf.renderer().setRenderableAngle(curAngle + 90);
        }
    }

    private void setTransitions() {
        for (Leaf leaf : leaves) {
            leaf.addTransition();
        }
    }

}
