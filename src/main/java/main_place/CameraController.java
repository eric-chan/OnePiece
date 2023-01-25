package main_place;

import characters.GameObject;
import characters.Monster;
import characters.Trape;
import objects.Reward;
import objects.StaticEnvironmentalObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CameraController {
    private MyPanel panel;
    private GameObject focus;

    // Camera control const params...  // +---------5---------+
    public int TileViewX = 16 / 2;     // |<-- 8 -- c -- 8 -->| , might need to add one on edge... not sure
    public int TileViewY = 10 / 2;     // +---------5---------+

    // Camera control params...
    private int PixelPerTile = 3 * 16;
    
    public int getPixelPerTile() {
        return PixelPerTile;
    }

    public void setPixelPerTile(int pixelPerTile) {
        PixelPerTile = pixelPerTile;
    }

    
    public CameraController(MyPanel panel) {
        this.panel = panel;
    }

    public void draw(Graphics2D g2) {
        //Draw tiles first
        if(panel.mapGenerator.MapImage == null)
            panel.mapGenerator.updateMapImage(PixelPerTile);
        drawTileMap(panel.mapGenerator.MapImage, g2);

        //Draw environments
        for (StaticEnvironmentalObject seo : panel.L_SEObjects)
            drawStaticEnvironmentObject(seo, g2);

        //Then draw characters.
        for (Reward r : panel.L_Rewards)
            drawObjectTileCenter(r, g2);
        for (Monster m : panel.L_Monsters)
            drawObjectTileCenter(m, g2, -0.5f, -0.8f);
        for (Trape t : panel.L_Trape)
            drawObjectTileSquare(t, g2);

        //Finally draw player
        drawObjectTileCenter(panel.player, g2, -0.5f, -0.8f);
    }

    public void setFocus(GameObject focus){
        this.focus = focus;
    }


    public void drawObjectTileCenter(GameObject o, Graphics2D g2){
        drawObjectTileCenter(o,g2,0f,0f);
    }

    public void drawObjectTileCenter(GameObject o, Graphics2D g2, float shiftX, float shiftY){
        BufferedImage image = o.getCurrentImage();
        g2.drawImage(image,
                (int)((o.getX() - focus.getX() + shiftX + TileViewX) * PixelPerTile + (PixelPerTile - image.getWidth()) / 2),
                (int)((o.getY() - focus.getY() + shiftY + TileViewY) * PixelPerTile + (PixelPerTile - image.getHeight()) / 2),
                (int)(PixelPerTile / 48.0f * image.getWidth()),
                (int)(PixelPerTile / 48.0f * image.getHeight()),
                null);
    }

    public void drawStaticEnvironmentObject(StaticEnvironmentalObject o, Graphics2D g2){
        BufferedImage image = getBufferedImage(o);
        g2.drawImage(image,
                (int)((o.getX() - focus.getX() + TileViewX) * PixelPerTile),
                (int)((o.getY() - focus.getY() + TileViewY) * PixelPerTile),
                PixelPerTile * o.getSizeX(),
                PixelPerTile * o.getSizeY(),
                null);
    }

    private BufferedImage getBufferedImage(StaticEnvironmentalObject o) {
        BufferedImage image = o.getCurrentImage();
        if(image != null)
            image = ScaledImage.scaledImage(image,
                    PixelPerTile * o.getSizeX(),
                    PixelPerTile * o.getSizeY());
        return image;
    }

    // TODO: USE THIS FUNCTION ONLY WHEN WINDOW SIZE IS FIXED!
    public void drawObjectTileSquare(GameObject o, Graphics2D g2){
        BufferedImage image = o.getCurrentImage();
        g2.drawImage(image,
                (int)((o.getX() - focus.getX() + TileViewX) * PixelPerTile),
                (int)((o.getY() - focus.getY() + TileViewY) * PixelPerTile),
                PixelPerTile,
                PixelPerTile,
                null);
    }

    public void drawTileMap(BufferedImage mapImg, Graphics2D g2){
        g2.drawImage(mapImg,
                0,
                0,
                TileViewX * 2 * PixelPerTile,
                TileViewY * 2 * PixelPerTile,
                (int)((focus.getX() - TileViewX) * PixelPerTile),
                (int)((focus.getY() - TileViewY) * PixelPerTile),
                (int)((focus.getX() + TileViewX) * PixelPerTile),
                (int)((focus.getY() + TileViewY) * PixelPerTile),
                null);
    }
}
