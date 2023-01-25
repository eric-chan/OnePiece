package objects;

import java.awt.image.BufferedImage;

import characters.GameObject;
import main_place.MyPanel;

/**
 * Sets properties for the reward
 */
public class Reward extends GameObject {
    public BufferedImage img;
    public String rwdType;

    public void applyReward(){
        mp.ui.showMessage(rwdType + " acquired");
    }

    public Reward(MyPanel mp) {
        super(mp);
        loadImage();
    }

    public Reward(MyPanel mp, int x, int y, String rwdType) {
        super(mp, x, y);
        this.rwdType = rwdType;
        loadImage();
    }

    public void update(){
        if(isInSameTileWith(mp.player)) {
            applyReward();
            isAlive = false;
        }
    }

    public void loadImage(){
    }

    public BufferedImage getCurrentImage() {
        return img;
    }
}

