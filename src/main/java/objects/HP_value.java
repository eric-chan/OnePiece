package objects;

import main_place.MyPanel;
import main_place.ScaledImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Shows the HP value as hearts on the UI
 * Important so the player knows how much health they have left
 * Once health is 0, game over
 * Health is displayed as five hearts, there are half hearts and 0 hearts
 * to signify the progressing loss of hearts 
 */
public class HP_value extends Reward{
    public BufferedImage img_fullHP;
    public BufferedImage img_halfHP;
    public BufferedImage img_emptyHP;

    public HP_value(MyPanel mp){
        super(mp);
    }

    public HP_value(MyPanel mp, int x, int y) {
        super(mp, x, y, "HP");
    }

    public void loadImage(){
        try{
            img_fullHP = ImageIO.read(new File("src/main/java/image/player/HP/heart.png")); //enter the location of diamond img;
            img_halfHP = ImageIO.read(new File("src/main/java/image/player/HP/halfheart.png")); //enter the location of diamond img;
            img_emptyHP = ImageIO.read(new File("src/main/java/image/player/HP/0heart.png")); //enter the location of diamond img;
            img_fullHP = ScaledImage.scaledImage(img_fullHP, 48, 48);
            img_halfHP = ScaledImage.scaledImage(img_halfHP, 48, 48);
            img_emptyHP = ScaledImage.scaledImage(img_emptyHP, 48, 48);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}