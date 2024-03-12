package pepse.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.NoiseGenerator;

public class Terrain {
    private final float groundHeightAtX0;
    private static final Color BASE_GROUND_COLOR = new Color(212, 123,74);
    private static final int TERRAIN_DEPTH = 20;
    private final NoiseGenerator noiseGenerator;
    private double seed;
    
    public Terrain(Vector2 windowDimensions, int seed){
            this.groundHeightAtX0 = (float) (windowDimensions.y()*(2.0/3.0));
            this.seed = seed;
            this.noiseGenerator = new NoiseGenerator(this.seed, (int)groundHeightAtX0);
        }

    public float groundHeightAt(float x) {
            float noise = (float) noiseGenerator.noise(x, Block.SIZE *7);
            return groundHeightAtX0 + noise;
        }
    public List<Block> createInRange(int minX, int maxX) {
        List<Block> blocks = new ArrayList<>();
        for (int x = minX; x <= maxX; x+= Block.SIZE) {
            for (int i = 0; i < TERRAIN_DEPTH; i++) {
                float block_height = (float)Math.floor(groundHeightAt(x) / Block.SIZE) * 
                Block.SIZE + i*Block.SIZE;
                Vector2 block_position = new Vector2(x, block_height);
                Renderable renderable = new RectangleRenderable
                (ColorSupplier.approximateColor(BASE_GROUND_COLOR));
                Block block = new Block(block_position, renderable);
                blocks.add(block);
            }
        }
        return blocks;
    }
}
