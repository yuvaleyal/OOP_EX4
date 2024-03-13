package pepse.world;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import java.awt.event.KeyEvent;


public class Avatar extends GameObject{
    private final static float AVATARSIZE = 50f;
    private final static String AVATERORIGINALPIC = "Pepse\\src\\pepse\\assets\\assets\\idle_0.png";
    private static final float GRAVITY = 600;
    private static final float VELOCITY_X = 400;
    private static final float VELOCITY_Y = -650;
    private float energy = 100f;
    private UserInputListener inputListener;

    public Avatar(Vector2 pos, UserInputListener inputListener, ImageReader imageReader){
        super(pos, Vector2.ONES.mult(AVATARSIZE),
         imageReader.readImage(AVATERORIGINALPIC, true));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(GRAVITY);
        this.inputListener = inputListener;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float xVel = 0;
        int flag = 0;
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)){
            if (this.energy >= 0.5){
                xVel -= VELOCITY_X;
                this.energy = this.energy-0.5f;
                flag = 1;
            }
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            if (this.energy >= 0.5){
                xVel += VELOCITY_X;
                this.energy = this.energy-0.5f;
                flag = 1;
            }
        }
        transform().setVelocityX(xVel);
        if(inputListener.isKeyPressed(KeyEvent.VK_SPACE) && getVelocity().y() == 0){
            if (this.energy >= 10){
                transform().setVelocityY(VELOCITY_Y);
                this.energy = this.energy - 10f;
                flag = 1;
            }
        }
        if (flag == 0){
            this.energy++;
        }
    }
}
