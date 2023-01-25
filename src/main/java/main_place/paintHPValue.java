package main_place;

import characters.Player;
import objects.HP_value;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Sets the HP values for the player
 * Displays HP values based on what the HP of the character is
 */
public class paintHPValue {
    public MyPanel mp;
    public Player player;
    public BufferedImage full_HQ, half_HQ, blank_HQ;

    public paintHPValue(MyPanel mp, Player p) {
        this.player = p;
        this.mp = mp;
        HP_value HP = new HP_value(mp);
        full_HQ = HP.img_fullHP;
        half_HQ = HP.img_halfHP;
        blank_HQ = HP.img_emptyHP;
    }


    public void presentHPValue(Graphics2D g2) {
        int x = 0;
        int y = 0;
        int HPpresent = player.currHPvalue;
        for(int i=0;i<player.fullHPvalue;i++){
            g2.drawImage(blank_HQ, x, y, null);
            x+=mp.cameraCtl.getPixelPerTile();
        }
        x = 0;
        y = 0;
        for(int i=0;i<player.currHPvalue;i++){
            g2.drawImage(full_HQ, x, y, null);
            x+=mp.cameraCtl.getPixelPerTile();
        }
    }


}