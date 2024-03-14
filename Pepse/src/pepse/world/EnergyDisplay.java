package pepse.world;

import java.util.function.Supplier;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

public class EnergyDisplay extends GameObject{
    private final Supplier<Float> getter;

    public EnergyDisplay(Supplier<Float> get){ 
        super(Vector2.ZERO,Vector2.ONES.mult(30) , new TextRenderable("100%"));
        getter = get;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Float amount = this.getter.get();

        renderer().setRenderable(new TextRenderable(String.valueOf(amount) + "%"));
    }


}
