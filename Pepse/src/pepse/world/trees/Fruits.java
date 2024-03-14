package pepse.world.trees;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.PepseGameManager;

/**
 * Represents a collection of fruits in the game.
 */
public class Fruits {
    private static final Random random = new Random();
    private static final double SPAWNING_PROBABILITY = 0.3;
    private static final Color INITIAL_COLOR = new Color(200, 20, 144);
    private List<Fruit> fruits;

    /**
     * Constructs a collection of fruits with the specified number of fruits and
     * bottom-middle position.
     * 
     * @param numOfFruits  The number of fruits to generate.
     * @param bottomMiddle The bottom-middle position for arranging the fruits.
     */
    public Fruits(int numOfFruits, Vector2 bottomMiddle) {
        int fruitSize = Leaf.getSize();
        fruits = new ArrayList<Fruit>(numOfFruits * numOfFruits);
        float initialX = bottomMiddle.x() - ((numOfFruits - 1) / 2 * fruitSize);
        float y = bottomMiddle.y() - (numOfFruits * fruitSize);
        float x;
        for (int i = 0; i < numOfFruits; i++) {
            x = initialX;
            for (int j = 0; j < numOfFruits; j++) {
                if (random.nextDouble() < SPAWNING_PROBABILITY) {
                    fruits.add(new Fruit(new Vector2(x, y), INITIAL_COLOR));
                }
                x += fruitSize;
            }
            y += fruitSize;
        }
    }

    /**
     * Adds the fruits to the specified game object collection.
     * 
     * @param collection The game object collection to which the fruits will be
     *                   added.
     */
    public void addFruits(GameObjectCollection collection) {
        for (Fruit fruit : fruits) {
            collection.addGameObject(fruit);
        }
    }

    /**
     * Changes the color of all the fruits.
     * 
     * @param color The new color to apply to the fruits.
     */
    public void changeColor(Color color) {
        for (Fruit fruit : fruits) {
            fruit.renderer().setRenderable(new OvalRenderable(color));
        }
    }
}
