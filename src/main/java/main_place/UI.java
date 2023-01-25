package main_place;

import objects.GoldenCoin;
import objects.Diamond;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * Defines User Interface with relevant elements:
 *      coin and diamond count
 *      item acquisition notification
 *      timer
 */
public class UI {

    MyPanel pnl;
    Font calibri40;
    BufferedImage coinIcon;
    BufferedImage dmdIcon;
    public boolean msgStatus = false;
    public String msg = "";
    int msgCounter = 0;
    double playTime;
    DecimalFormat decimals = new DecimalFormat("#0.0");


    public UI(MyPanel mp) {
        this.pnl = mp;

        calibri40 = new Font("Calibri", Font.PLAIN, 40);

        GoldenCoin coin = new GoldenCoin(mp);
        coinIcon = coin.img;

        Diamond dmd = new Diamond(mp);
        dmdIcon = dmd.img;

    }

    public void showMessage (String text) {
        msg = text;
        msgStatus = true;
    }

    public void draw(Graphics2D g2){

        //Item count UI
        g2.setFont(calibri40);
        g2.setColor(Color.white);

        g2.drawImage(coinIcon, 650 - pnl.cameraCtl.getPixelPerTile()/2, pnl.cameraCtl.getPixelPerTile()/2, pnl.cameraCtl.getPixelPerTile(), pnl.cameraCtl.getPixelPerTile(), null);
        g2.drawString("x  "+ pnl.coinNumber, 768 - 80,62 );

        g2.drawImage(dmdIcon, 650 - pnl.cameraCtl.getPixelPerTile()/2, pnl.cameraCtl.getPixelPerTile()*5/3, pnl.cameraCtl.getPixelPerTile(), pnl.cameraCtl.getPixelPerTile(), null);
        g2.drawString("x  "+ pnl.diamondNumber, 768 - 80,pnl.cameraCtl.getPixelPerTile()*7/6+62 );

        //Game stopwatch
        playTime += (double)1/120;
        g2.drawString("Time: " + decimals.format(playTime), 320 ,pnl.cameraCtl.getPixelPerTile() - 10 );

        //message pop-up
        if(msgStatus == true){

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(msg, pnl.cameraCtl.getPixelPerTile()/2, pnl.cameraCtl.getPixelPerTile()*5);

            msgCounter++;

            if(msgCounter > 240){
                msgStatus = false;
                msgCounter = 0;
            }
        }
    }
}
