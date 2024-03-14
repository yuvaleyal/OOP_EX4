package pepse.world.trees;

import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

/**
 * Represents a tree object in the game.
 */
public class Tree {
    private final Trunk trunk;
    private final Leaves leaves;
    private final Fruits fruits;

    /**
     * Constructs a tree with the specified height, number of leaves, and position.
     * 
     * @param height      The height of the tree trunk.
     * @param numOfLeaves The number of leaves on the tree.
     * @param placeToPut  The position to place the tree.
     */
    public Tree(int height, int numOfLeaves, Vector2 placeToPut) {
        trunk = new Trunk(placeToPut, height);
        Vector2 trunkTop = trunk.getTopLeftCorner();
        leaves = new Leaves(numOfLeaves, trunkTop);
        fruits = new Fruits(numOfLeaves, trunkTop);
    }

    /**
     * Adds the tree to the specified game object collection.
     * 
     * @param collection The game object collection to which the tree will be added.
     */
    public void addTree(GameObjectCollection collection) {
        trunk.addTrunk(collection);
        leaves.addLeaves(collection);
        fruits.addFruits(collection);
    }
}
