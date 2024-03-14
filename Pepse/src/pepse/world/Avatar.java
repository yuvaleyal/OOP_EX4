package pepse.world;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.AnimationRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import java.awt.event.KeyEvent;

/**
 * Represents the avatar character in the game.
 */
public class Avatar extends GameObject {
    private final static float AVATARSIZE = 50f;
    private final static String AVATERORIGINALPIC = "Pepse\\src\\pepse\\assets\\assets\\idle_0.png";
    private static final float GRAVITY = 600;
    private static final float VELOCITY_X = 400;
    private static final float VELOCITY_Y = -300;
    private static final String AVATARTAG = "avatar";
    private final float RUNNINGCOST = 0.5f;
    private final float JUMPCOST = 10f;
    private final float ANIMATINGTIME = 0.2f;
    private final float MAXENERGY = 100;
    private final String[] IDLEPICS = new String[] {
            "Pepse\\src\\pepse\\assets\\assets\\idle_0.png",
            "Pepse\\src\\pepse\\assets\\assets\\idle_1.png",
            "Pepse\\src\\pepse\\assets\\assets\\idle_2.png",
            "Pepse\\src\\pepse\\assets\\assets\\idle_3.png" };
    private final String[] JUMPPICS = new String[] {
            "Pepse\\src\\pepse\\assets\\assets\\jump_0.png",
            "Pepse\\src\\pepse\\assets\\assets\\jump_1.png",
            "Pepse\\src\\pepse\\assets\\assets\\jump_2.png",
            "Pepse\\src\\pepse\\assets\\assets\\jump_3.png" };
    private final String[] RUNNINGPICS = new String[] {
            "Pepse\\src\\pepse\\assets\\assets\\run_0.png",
            "Pepse\\src\\pepse\\assets\\assets\\run_1.png",
            "Pepse\\src\\pepse\\assets\\assets\\run_2.png",
            "Pepse\\src\\pepse\\assets\\assets\\run_3.png",
            "Pepse\\src\\pepse\\assets\\assets\\run_4.png",
            "Pepse\\src\\pepse\\assets\\assets\\run_5.png" };
    private final Renderable[] IDLEFRAMES = new Renderable[4];
    private final Renderable[] JUMPFRAMES = new Renderable[4];
    private final Renderable[] RUNNINGFRAMES = new Renderable[6];
    private float energy = 100f;
    private UserInputListener inputListener;
    private AnimationRenderable idleAnimationRenderable;
    private AnimationRenderable jumpingAnimationRenderable;
    private AnimationRenderable runningAnimationRenderable;

    /**
     * Constructs an Avatar object with the specified position, input listener, and
     * image reader.
     * 
     * @param pos           The position of the avatar.
     * @param inputListener The input listener for controlling the avatar.
     * @param imageReader   The image reader for reading avatar images.
     */
    public Avatar(Vector2 pos, UserInputListener inputListener, ImageReader imageReader) {
        super(pos, Vector2.ONES.mult(AVATARSIZE),
                imageReader.readImage(AVATERORIGINALPIC, true));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(GRAVITY);
        this.inputListener = inputListener;
        int i = 0;
        for (String pic : this.IDLEPICS) {
            this.IDLEFRAMES[i] = imageReader.readImage(pic, true);
            i++;
        }
        i = 0;
        for (String pic : this.JUMPPICS) {
            this.JUMPFRAMES[i] = imageReader.readImage(pic, true);
            i++;
        }
        i = 0;
        for (String pic : this.RUNNINGPICS) {
            this.RUNNINGFRAMES[i] = imageReader.readImage(pic, true);
            i++;
        }
        this.runningAnimationRenderable = new AnimationRenderable(RUNNINGFRAMES,
                ANIMATINGTIME);
        this.jumpingAnimationRenderable = new AnimationRenderable(JUMPFRAMES,
                ANIMATINGTIME);
        this.idleAnimationRenderable = new AnimationRenderable(IDLEFRAMES, ANIMATINGTIME);
        this.renderer().setRenderable(idleAnimationRenderable);
        this.setTag(AVATARTAG);
    }

    /**
     * Updates the avatar's state based on the input and time elapsed.
     * 
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float xVel = 0;
        int flag = 0;
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (this.energy >= RUNNINGCOST) {
                xVel -= VELOCITY_X;
                this.energy = this.energy - RUNNINGCOST;
                flag = 1;
                this.renderer().setRenderable(runningAnimationRenderable);
                this.renderer().setIsFlippedHorizontally(true);
            }
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (this.energy >= RUNNINGCOST) {
                xVel += VELOCITY_X;
                this.energy = this.energy - RUNNINGCOST;
                flag = 1;
                this.renderer().setRenderable(runningAnimationRenderable);
                this.renderer().setIsFlippedHorizontally(false);
            }
        }
        transform().setVelocityX(xVel);
        if (inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0) {
            if (this.energy >= JUMPCOST) {
                transform().setVelocityY(VELOCITY_Y);
                this.energy = this.energy - JUMPCOST;
                flag = 1;
                this.renderer().setRenderable(jumpingAnimationRenderable);
            }
        }
        if (getVelocity().y() != 0) {
            flag = 1;
        }
        if (flag == 0) {
            if (this.energy < MAXENERGY) {
                if (this.energy + 1 > MAXENERGY) {
                    this.energy = MAXENERGY;
                }
                else {
                    this.energy += 0.1; //////////////
                }
            }
            this.renderer().setRenderable(idleAnimationRenderable);
        }
    }

    /**
     * Increases the energy of the avatar by the specified amount.
     * 
     * @param energyToGain The amount of energy to increase.
     */
    public void energyGain(float energyToGain) {
        if (this.energy + energyToGain > MAXENERGY) {
            this.energy = MAXENERGY;
        }
        else {
            this.energy = this.energy + energyToGain;
        }
    }

    /**
     * Retrieves the energy of the avatar.
     * 
     * @return The energy of the avatar.
     */
    public float getEnergy() {
        return this.energy;
    }

    /**
     * Retrieves the tag of the avatar.
     * 
     * @return The tag of the avatar.
     */
    public static String getAvatarTag() {
        return AVATARTAG;
    }
}
