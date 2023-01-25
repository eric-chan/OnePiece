package objects;

import board.MapGenerator;
import main_place.MyPanel;
import main_place.ScaledImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Platform extends StaticEnvironmentalObject {

    private BufferedImage img;
    public Platform(MyPanel mp) {
        super(mp);
    }

    public Platform(MyPanel mp, float x, float y, int sizeX, int sizeY, String displayType) {
        super(mp, x, y, sizeX, sizeY, displayType);
        loadImage();
    }

    public void loadImage(){
        try {
            img = ImageIO.read(new File("src/main/java/image/map/house1.png"));
            if(img != null){
                img = ScaledImage.scaledImage(img,
                        mp.cameraCtl.getPixelPerTile() * sizeX,
                        mp.cameraCtl.getPixelPerTile() * sizeY);
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ApplyMapTileCollision(MapGenerator mapGenerator) {
        mapGenerator.setTileOn((int)x + 0,(int)y + 0,  7);
        mapGenerator.setTileOn((int)x + 1,(int)y + 0,  7);
        mapGenerator.setTileOn((int)x + 2,(int)y + 0,  7);
        mapGenerator.setTileOn((int)x + 0,(int)y + 1,  7);
        mapGenerator.setTileOn((int)x + 2,(int)y + 1,  7);
        mapGenerator.setTileOn((int)x + 0,(int)y + 2,  7);
        mapGenerator.setTileOn((int)x + 2,(int)y + 2,  7);
    }

    public BufferedImage getCurrentImage(){
        return img;
    }
}
