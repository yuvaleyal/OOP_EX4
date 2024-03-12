package pepse.world;

import danogl.util.Vector2;

public class Terrain {
    private final float groundHeightAtX0;
    
    public Terrain(Vector2 windowDimensions, int seed){
            groundHeightAtX0 = (float) (windowDimensions.y()*(2.0/3.0));
        }
}
