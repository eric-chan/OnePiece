package board;

import java.awt.image.BufferedImage;

/**
 * Defines properties of the map tiles
 * Collision boolean set to true for walls and barriers
 */
public class Tile {

    public BufferedImage image;
    public boolean collision = false;
}
