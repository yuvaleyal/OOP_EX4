package pepse.world.trees;

import java.util.HashSet;

import danogl.collisions.GameObjectCollection;
import danogl.util.Vector2;

public class Tree {
    private final Trunk trunk;
    private final Leaves leaves;

    public Tree(int height, int numOfLeaves, Vector2 placeToPut) {
        trunk = new Trunk(placeToPut, height);
        Vector2 trunkTop = trunk.getTopLeftCorner();
        leaves = new Leaves(numOfLeaves, trunkTop);
    }

    public void addTree(GameObjectCollection collection) {
        trunk.addTrunk(collection);
        leaves.addLeaves(collection);
    }
}
