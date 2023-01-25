package main_place;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndUI {
    private MyPanel mp;
    private Keyreader kr;
    private long lastTextShownTime = 0;
    private static final int ScreenSplitCountX = 16 / 2;     //cool stuff
    private static final int ScreenSplitCountY = 10 / 2;
    private int[][] spiralMatrix;
    private int progress = 0;

    private BufferedImage reaperImg = null;
    private BufferedImage reaperImg_win = null;
    public boolean isLoose  = false;

    public EndUI(MyPanel mp, Keyreader kr2){
        this.mp = mp;
        kr=kr2;
        loadImage();
        spiralMatrix = getCoolEndingEffectSprialMatrix();
    }

    public void setDefault(){
        progress = 0;
    }

    public void loadImage(){
        try {
            reaperImg = ImageIO.read(new File("src/main/java/image/gui/end/GameOver.png"));
            reaperImg_win = ImageIO.read(new File("src/main/java/image/gui/end/Interface.png"));
        }catch (IOException ex){
            //Lazy
            System.out.println("Exception thrown by loadImage function, in class EndUI");
        }
    }

    public void update(){
        if((System.currentTimeMillis() - lastTextShownTime) > 50) {
            lastTextShownTime = System.currentTimeMillis();
            progress++;
        }
        if(kr.enterW) {
            mp.GameState = 1;   //You just drive the car, it's up to daddy(My_panel) to figure it out
            isLoose = false;
            this.mp.player.point = 0;
            this.mp.diamondNumber = 0;
            this.mp.coinNumber =0;
        }
    }

    public void draw(Graphics2D g2){
        if(isLoose){
            g2.drawImage(reaperImg_win,280,160,300,120,null);
            g2.drawString("You Win the Game!!!   ", 310,200 );
            g2.drawString("  Press Enter Twice to Play it Again  ", 345,240 );
        }
        else {
            int blockWidth = mp.frame.getWidth() / ScreenSplitCountX;
            int blockHeight = mp.frame.getHeight() / ScreenSplitCountY;
            int imgBlockWidth = reaperImg.getWidth() / ScreenSplitCountX;
            int imgBlockHeight = reaperImg.getHeight() / ScreenSplitCountY;
            for (int y = 0; y < ScreenSplitCountY; y++)
                for (int x = 0; x < ScreenSplitCountX; x++)
                    if (progress > spiralMatrix[y][x])
                        g2.drawImage(reaperImg,
                                x * blockWidth,
                                y * blockHeight,
                                (x + 1) * blockWidth,
                                (y + 1) * blockHeight,
                                x * imgBlockWidth,
                                y * imgBlockHeight,
                                (x + 1) * imgBlockWidth,
                                (y + 1) * imgBlockHeight,
                                null);
        }
    }


    private int[][] getCoolEndingEffectSprialMatrix(){
        int[][] matrix = new int[ScreenSplitCountY][ScreenSplitCountX];
        int xMax = ScreenSplitCountX;
        int yMax = ScreenSplitCountY;
        int x = 0;
        int y = 0;
        int num = 0;
        while (yMax > 0 && xMax > 0) {
            //Might need to handle 'single line' situation
            //Right
            for (int i = 0; i < xMax - 1; i++)
                matrix[x][y++] = num++;
            //Down
            for (int i = 0; i < yMax - 1; i++)
                matrix[x++][y] = num++;
            //Left
            for (int i = 0; i < xMax - 1; i++)
                matrix[x][y--] = num++;
            //Up
            for (int i = 0; i < yMax - 1; i++)
                matrix[x--][y] =num++;
            x++;
            y++;
            xMax -= 2;
            yMax -= 2;
        }
        //TODO: REMOVE DEBUG PRINT:
        return matrix;
    }

}
