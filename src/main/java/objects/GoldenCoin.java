package objects;

import main_place.MyPanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


/**
 * Regular reward Gold Coin
 * Obtains assets for the reward to display on the board
 */
public class GoldenCoin extends Reward{
    public GoldenCoin(MyPanel mp){
        super(mp);
    }
    public GoldenCoin(MyPanel mp, int x, int y){
        super(mp, x, y, "GoldenCoin");
    }

    @Override
    public void applyReward() {
        super.applyReward();
        mp.coinNumber++;
    }

    public void loadImage(){
        try {
            img = ImageIO.read(new File("src/main/java/image/reword/coin.png")); //enter the location of golden coin img;
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
