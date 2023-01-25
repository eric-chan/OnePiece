package objects;

import main_place.MyPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class House extends StaticEnvironmentalObject {

    private BufferedImage img;
    public House(MyPanel mp) {
        super(mp);
    }

    public House(MyPanel mp, float x, float y, int sizeX, int sizeY, String displayType) {
        super(mp, x, y, sizeX, sizeY, displayType);
        loadImage();
    }

    public void loadImage(){
        try {
            if(displayType == "Blue")
                img = ImageIO.read(new File("src/main/java/image/map/house1.png"));
            if(displayType == "Red")
                img = ImageIO.read(new File("src/main/java/image/map/house2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getCurrentImage(){
        return img;
    }
}
