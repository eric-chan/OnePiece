package objects;

import board.MapGenerator;
import characters.GameObject;
import main_place.MyPanel;

/**
 * Class for static enemies (traps)
 * Object is 1 tile long and 1 tile wide
 * Collides with the player and will cause damage to the player
 */
public class StaticEnvironmentalObject extends GameObject {
    protected String displayType = "None";    //"Blue" or "Red

    public String getDisplayType() {
        return displayType;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    protected int sizeX = 1;
    protected int sizeY = 1;

    public StaticEnvironmentalObject(MyPanel mp) {
        super(mp);
    }

    public StaticEnvironmentalObject(MyPanel mp, float x, float y, int sizeX, int sizeY, String displayType) {
        super(mp, x, y);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.displayType = displayType;
    }

    //append collision from top left corner, if size == 1, then only starting tile checks colliding
    public void ApplyMapTileCollision(MapGenerator mapGenerator){
        if(sizeX <= 0 || sizeY <= 0)
            return;
        for(int hY = 0; hY < sizeY; hY++)
            for(int hX = 0; hX < sizeX; hX++)
                mapGenerator.setTileOn((int)x + hX, (int)y +hY, MapGenerator.ME_UNWALKABLEGRASS);
    }

}
