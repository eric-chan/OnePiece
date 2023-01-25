package main_place;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

/**
 * Scales images based on the dimension of the board
 * and other objects (For different computer resolutions)
 */
public class ScaledImage {
    public static BufferedImage scaledImage(BufferedImage ori, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, ori.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(ori, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
