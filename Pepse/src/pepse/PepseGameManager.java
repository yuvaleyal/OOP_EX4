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
import pepse.world.Avatar;
import pepse.world.Block;
import pepse.world.Terrain;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;

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
        GameObject sun = Sun.create(windowDimensions, CYCLELENGTH*2);
        GameObject sunHalo = SunHalo.create(sun);
        sunHalo.addComponent((float deltaTime) -> {sunHalo.setCenter(sun.getCenter());});
        gameObjects().addGameObject(sun, Layer.BACKGROUND);
        gameObjects().addGameObject(sunHalo, Layer.BACKGROUND);
        Vector2 pos = new Vector2(20,terrain.groundHeightAt(20) - Block.SIZE);
        Avatar avater = new Avatar(pos, inputListener, imageReader);
        gameObjects().addGameObject(avater);
    }

    public static void main(String[] args) {
        PepseGameManager gameManager = new PepseGameManager();
        gameManager.run();
    }
}
