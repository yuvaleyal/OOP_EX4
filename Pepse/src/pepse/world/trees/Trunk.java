package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.world.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Trunk {
    private static final Color BASIC_COLOR = new Color(100, 50, 20);
    private final List<Block> trunk;
    private final Vector2 topLeftCorner;

    public Trunk(Vector2 bottomLeftCorner, int height) {
        trunk = new ArrayList<Block>(height);
        float x = bottomLeftCorner.x();
        float y = bottomLeftCorner.y();
        for (int blockNum = 0; blockNum < height; blockNum++) {
            y -= Block.getSize();
            trunk.add(new Block(new Vector2(x, y), new RectangleRenderable(BASIC_COLOR)));
        }
        topLeftCorner = new Vector2(x, y);
    }

    public void addTrunk(GameObjectCollection collection) {
        for (Block block : trunk) {
            collection.addGameObject(block, Layer.STATIC_OBJECTS);
        }
    }

    public Vector2 getTopLeftCorner() {
        return new Vector2(topLeftCorner.x(), topLeftCorner.y());
    }
}
