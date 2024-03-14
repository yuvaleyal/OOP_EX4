package pepse.world.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

public class Fruits {
    private static final Random random = new Random();
    private static final double SPAWNING_PROBABILITY = 0.3;
    private List<Fruit> fruits;

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
                    fruits.add(new Fruit(new Vector2(x, y)));
                }
                x += fruitSize;
            }
            y += fruitSize;
        }
    }

    public void addFruits(GameObjectCollection collection) {
        for (Fruit fruit : fruits) {
            collection.addGameObject(fruit);
        }
    }
}
