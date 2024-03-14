package pepse;

import java.awt.Color;
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
import pepse.world.EnergyDisplay;
import pepse.world.JumpObserver;
import pepse.world.Terrain;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.Flora;
import pepse.world.trees.Fruit;
import pepse.world.trees.Tree;

/**
 * Represents the game manager for the Pepse game.
 */
public class PepseGameManager extends GameManager implements JumpObserver {
    private final int CYCLELENGTH = 30;
    private static final int MAX_COLOR = 256;
    private List<Tree> trees;

    /**
     * Constructs a PepseGameManager object.
     */
    public PepseGameManager() {
        super();
    }

    /**
     * Initializes the Pepse game.
     * 
     * @param imageReader      The image reader for loading game images.
     * @param soundReader      The sound reader for loading game sounds.
     * @param inputListener    The user input listener for handling input events.
     * @param windowController The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
            UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Vector2 windowDimensions = windowController.getWindowDimensions();
        GameObject sky = Sky.create(windowDimensions);
        gameObjects().addGameObject(sky, Layer.BACKGROUND);
        int seed = (int) new Random().nextInt();
        Terrain terrain = new Terrain(windowDimensions, seed);
        List<Block> list_of_blocks = terrain.createInRange(0,
                (int) windowController.getWindowDimensions().x());
        for (Block block : list_of_blocks) {
            gameObjects().addGameObject(block);
        }
        GameObject night = Night.create(windowDimensions, CYCLELENGTH);
        gameObjects().addGameObject(night, Layer.UI);
        GameObject sun = Sun.create(windowDimensions, CYCLELENGTH * 2);
        GameObject sunHalo = SunHalo.create(sun);
        sunHalo.addComponent((float deltaTime) -> {
            sunHalo.setCenter(sun.getCenter());
        });
        gameObjects().addGameObject(sun, Layer.BACKGROUND);
        gameObjects().addGameObject(sunHalo, Layer.BACKGROUND);
        Vector2 pos = new Vector2(20, terrain.groundHeightAt(20) - Block.getSize());
        Avatar avater = new Avatar(pos, inputListener, imageReader);
        avater.registerObserver(this);
        gameObjects().addGameObject(avater);
        EnergyDisplay display = new EnergyDisplay(avater::getEnergy);
        gameObjects().addGameObject(display, Layer.UI);
        Flora flora = new Flora(x -> terrain.groundHeightAt(x));
        trees = flora.createInRange(0, (int) windowDimensions.x());
        addTreesToGame(trees);
        Fruit.setCycleLength(CYCLELENGTH);
    }

    public void onAvatarJump() {
        Color fruitColor = randomColor();
        for (Tree tree : trees) {
            tree.onAvatarJump(fruitColor);
        }
    }

    private void addTreesToGame(List<Tree> trees) {
        for (Tree tree : trees) {
            tree.addTree(gameObjects());
        }
    }

    public static Color randomColor() {
        Random random = new Random();
        return new Color(random.nextInt(0, MAX_COLOR), random.nextInt(0, MAX_COLOR),
                random.nextInt(0, MAX_COLOR));
    }

    /**
     * The main method to start the Pepse game.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        PepseGameManager gameManager = new PepseGameManager();
        gameManager.run();
    }
}
