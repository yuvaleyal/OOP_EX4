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

/**
 * Represents the terrain in the game environment.
 */
public class Terrain {
    private final float groundHeightAtX0;
    private static final Color BASE_GROUND_COLOR = new Color(212, 123,74);
    private static final int TERRAIN_DEPTH = 20;
    private final double GROUNDHEIGHTMULT = 2.0/3.0;
    private final int NOISEGENMULT = 7;
    private final NoiseGenerator noiseGenerator;
    private double seed;
    
    /**
     * Constructs a Terrain object with the specified window dimensions and seed.
     * @param windowDimensions The dimensions of the game window.
     * @param seed The seed used for generating pseudo-random noise.
     */
    public Terrain(Vector2 windowDimensions, int seed){
            this.groundHeightAtX0 = (float) (windowDimensions.y()*(GROUNDHEIGHTMULT));
            this.seed = seed;
            this.noiseGenerator = new NoiseGenerator(this.seed, (int)groundHeightAtX0);
        }

    /**
     * Gets the ground height at the specified x-coordinate.
     * @param x The x-coordinate.
     * @return The ground height at the specified x-coordinate.
     */
    public float groundHeightAt(float x) {
            float noise = (float) noiseGenerator.noise(x, Block.getSize() * NOISEGENMULT);
            return groundHeightAtX0 + noise;
        }
    
     /**
     * Creates a list of blocks within the specified range.
     * @param minX The minimum x-coordinate.
     * @param maxX The maximum x-coordinate.
     * @return The list of blocks within the specified range.
     */
    public List<Block> createInRange(int minX, int maxX) {
        List<Block> blocks = new ArrayList<>();
        for (int x = minX; x <= maxX; x+= Block.getSize()) {
            for (int i = 0; i < TERRAIN_DEPTH; i++) {
                float block_height = (float)Math.floor(groundHeightAt(x) / Block.getSize()) * 
                Block.getSize() + i*Block.getSize();
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
