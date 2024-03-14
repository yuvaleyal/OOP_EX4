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

public class Leaves {
    private static final Random random = new Random();
    private static final double SPAWNING_PROBABILITY = 0.6;
    private static final int LEAVES_LAYER = -50;
    private List<Leaf> leaves;

    /**
     * 
     * @param numOfLeaves: expects a non-even number
     * @param bottomMiddle
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

    public void addLeaves(GameObjectCollection collection) {
        for (Leaf leaf : leaves) {
            collection.addGameObject(leaf, LEAVES_LAYER);
        }
    }

    private void setTransitions() {
        for (Leaf leaf : leaves) {
            leaf.addTransition();
        }
    }

}
