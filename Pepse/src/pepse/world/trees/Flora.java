package pepse.world.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import danogl.util.Vector2;
import pepse.world.Block;

public class Flora {
    private static final int MAX_HEIGHT = 10;
    private static final int NUM_OF_LEAVES = 7;
    private Function<Float, Float> terrainHeight;

    public Flora(Function<Float, Float> terrainHeight) {
        this.terrainHeight = terrainHeight;
    }

    public List<Tree> createInRange(int minX, int maxX) {
        List<Tree> trees = new ArrayList<>();
        Random random = new Random();
        for (float location = minX; location < maxX; location += Block.getSize()) {
            if (random.nextDouble() < 0.1) {
                int height = random.nextInt(1, MAX_HEIGHT);
                trees.add(new Tree(height, NUM_OF_LEAVES,
                        new Vector2(location, terrainHeight.apply(location))));
            }
        }
        return trees;
    }

}
