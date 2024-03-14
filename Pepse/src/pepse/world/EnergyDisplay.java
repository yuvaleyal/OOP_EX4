package pepse.world;

import java.util.function.Supplier;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

/**
 * Represents an energy display object in the game.
 */
public class EnergyDisplay extends GameObject{
    private final Supplier<Float> getter;

    /**
     * Constructs an EnergyDisplay object with the specified getter.
     * 
     * @param get The supplier function to get the energy value.
     */
    public EnergyDisplay(Supplier<Float> get){ 
        super(Vector2.ZERO,Vector2.ONES.mult(30) , new TextRenderable("100%"));
        getter = get;
    }

     /**
     * Updates the energy display based on the energy value obtained from the supplier.
     * 
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Float amount = this.getter.get();

        renderer().setRenderable(new TextRenderable(String.valueOf(amount) + "%"));
    }


}
