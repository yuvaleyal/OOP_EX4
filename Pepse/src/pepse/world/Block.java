package pepse.world;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
/**
 * Represents a block in the game environment.
 */
public class Block extends GameObject{
    private static final int SIZE = 30;
    private static final String BLOCKTAG = "ground_block";
    
    /**
    * Constructs a Block object with the specified top-left corner position and renderable.
    * @param topLeftCorner The top-left corner position of the block.
    * @param renderable The renderable object to be displayed for the block.
    */
    public Block(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner, Vector2.ONES.mult(SIZE), renderable);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
        setTag(BLOCKTAG);
    }

    /**
    * Retrieves the size of a block.
    * @return The size of a block.
    */
    public static int getSize(){
        return Block.SIZE;
    }
}
