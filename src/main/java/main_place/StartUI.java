package main_place;

import board.MapGenerator;

import java.awt.*;
import java.io.IOException;


/**
 * Represents the start up menu once game is launched
 */
public class StartUI {
    private MyPanel mp;
    private Keyreader kr;
    private long lastTextShownTime = 0;
    private boolean ifShowStartHint = false;


    public StartUI(MyPanel mp, Keyreader kr2) throws IOException {
        this.mp = mp;
        kr=kr2;
    }

    public void update(){
        if((System.currentTimeMillis() - lastTextShownTime) > 500) {
            lastTextShownTime = System.currentTimeMillis();
            ifShowStartHint = !ifShowStartHint;
        }
        if(kr.enterW) {
            mp.mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel1);
            mp.mapGenerator.updateMapImage(mp.cameraCtl.getPixelPerTile());
            mp.GameState = 1;
        }
    }

    public void draw(Graphics2D g2){
        if(mp.GameState ==0){
            Font font = new Font("Verdana", Font.BOLD, 36);
            g2.setFont(font);
            if(ifShowStartHint)
                g2.drawString("PRESS [ENTER] TO START!", 48,48);
        }
    }
}
