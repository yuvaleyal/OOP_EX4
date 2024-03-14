package pepse.world.trees;

import java.awt.Color;
import java.util.Random;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.ScheduledTask;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.world.Avatar;
import pepse.world.Block;

/**
 * Represents a fruit block in the game.
 */
public class Fruit extends Block {
    private static final int ENERGY_FROM_EATING = 10;
    private static float cycleLength;
    private boolean isInGame;

    /**
     * Constructs a Fruit object with the specified top-left corner position.
     * 
     * @param topLeftCorner The top-left corner position of the fruit block.
     */
    public Fruit(Vector2 topLeftCorner, Color color) {
        super(topLeftCorner, new OvalRenderable(color));
        isInGame = true;
    }

    public static void setCycleLength(float length) {
        cycleLength = length;
    }

    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other.getTag().equals(Avatar.getAvatarTag()) && isInGame;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (shouldCollideWith(other)) {
            Avatar avatar = (Avatar) other;
            avatar.energyGain(ENERGY_FROM_EATING);
            removeFromGame();
            super.onCollisionEnter(other, collision);
        }
    }

    private void removeFromGame() {
        isInGame = false;
        this.renderer().setOpaqueness(0);
        new ScheduledTask(this, cycleLength, false, () -> addToGame());
    }

    private void addToGame() {
        isInGame = true;
        this.renderer().setOpaqueness(100f);
    }
}
