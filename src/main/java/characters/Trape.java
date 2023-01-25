package characters;

import main_place.MyPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Creates a trap class that extends the character class
 * Sets up and initializes static enemies
 */
public class Trape extends GameObject {
    public long lastColTime;
    public boolean lastCol;                 //last update in Collision Zone?
    public final int BubbleTime = 2000;     //On damage time (WOW)
    public BufferedImage image;

    public Trape(MyPanel mp, int x, int y) {
        super(mp, x, y);
        loadImage();
        lastColTime = 0;
        lastCol = false;
    }

    public void loadImage() {
        try {
            image = ImageIO.read(new File("src/main/java/image/enemy/trap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates player position based on keyboard input
     * Four directions, up down left and right
     */
    public void update() {
        if(this.mp.player.MainPlayer_is_dead()) {
            this.mp.player.getKr().escW= true;
            return ;
        }
        if (isInSameTileWith(mp.player)) {
            if(lastCol){
                if((System.currentTimeMillis() - lastColTime)> BubbleTime) {
                    lastColTime = System.currentTimeMillis();
                    mp.player.currHPvalue -= 1;
                    this.mp.sound.PLayMusicOnce(3);
                }
            }else{
                lastColTime = System.currentTimeMillis();
                this.mp.sound.PLayMusicOnce(3);
                mp.player.currHPvalue -= 1;
            }
            lastCol = true;
        }else{
            lastCol = false;
        }
    }

    //
    public BufferedImage getCurrentImage() {
        return image;
    }




}
