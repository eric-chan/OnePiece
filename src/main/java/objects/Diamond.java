package objects;

import main_place.MyPanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Special Reward Diamond
 * Obtains asset for the diamond
 */
public class Diamond extends Reward{
    public Diamond(MyPanel mp){
        super(mp);
    }
    public Diamond(MyPanel mp, int x, int y) {
        super(mp, x, y, "Diamond");
    }

    @Override
    public void applyReward() {
        super.applyReward();
        mp.diamondNumber++;
    }

    public void loadImage(){
        try {
            img = ImageIO.read(new File("src/main/java/image/reword/dimond1.png")); //enter the location of diamond img;
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
