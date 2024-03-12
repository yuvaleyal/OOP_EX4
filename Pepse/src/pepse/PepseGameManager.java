package pepse;

import java.util.List;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.GameGUIComponent;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import pepse.world.Sky;
import pepse.world.Block;
import pepse.world.Terrain;

public class PepseGameManager extends GameManager {
    public PepseGameManager() {
        super();
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
            UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        GameObject sky = Sky.create(windowController.getWindowDimensions());
        gameObjects().addGameObject(sky, Layer.BACKGROUND);
        Terrain terrain = new Terrain(windowController.getWindowDimensions(), 0);
        List<Block> list_of_blocks = terrain.createInRange
        (0, (int)windowController.getWindowDimensions().x());
        for (Block block : list_of_blocks) {
            gameObjects().addGameObject(block);
        }

    }

    public static void main(String[] args) {
        PepseGameManager gameManager = new PepseGameManager();
        gameManager.run();
    }
}
