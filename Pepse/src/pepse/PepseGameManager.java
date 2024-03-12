package pepse;

import java.util.List;
import java.util.Random;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.GameGUIComponent;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import pepse.world.Sky;
import pepse.world.Block;
import pepse.world.Terrain;
import pepse.world.daynight.Night;

public class PepseGameManager extends GameManager {
    private final int CYCLELENGTH = 30;
    public PepseGameManager() {
        super();
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
            UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Vector2 windowDimensions = windowController.getWindowDimensions();
        GameObject sky = Sky.create(windowDimensions);
        gameObjects().addGameObject(sky, Layer.BACKGROUND);
        int seed = (int)new Random().nextGaussian();
        Terrain terrain = new Terrain(windowDimensions, seed);
        List<Block> list_of_blocks = terrain.createInRange
        (0, (int)windowController.getWindowDimensions().x());
        for (Block block : list_of_blocks) {
            gameObjects().addGameObject(block);
        }
        GameObject night = Night.create(windowDimensions, CYCLELENGTH);
        gameObjects().addGameObject(night, Layer.UI);
    }

    public static void main(String[] args) {
        PepseGameManager gameManager = new PepseGameManager();
        gameManager.run();
    }
}
