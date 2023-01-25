package main_place;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Extremely important class
 * Sets up and recognizes keyboard input from the user
 * ex. for movement, to start the game, etc.
 */
public class Keyreader implements KeyListener {

    public boolean leftW,rightW,upW,downW,enterW,escW;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int getter = e.getKeyCode();
        if(getter ==KeyEvent.VK_ENTER){
            enterW= true;
        }
        if(getter==KeyEvent.VK_W){
            upW = true;
        }
        if(getter==KeyEvent.VK_S){
            downW=true;
        }
        if(getter==KeyEvent.VK_A){
            leftW=true;
        }
        if(getter==KeyEvent.VK_D){
            rightW=true;
        }
        if(getter==KeyEvent.VK_ESCAPE){
            escW=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int getter = e.getKeyCode();
        if(getter ==KeyEvent.VK_ENTER){
            enterW= false;
        }
        if(getter==KeyEvent.VK_W){
            upW = false;
        }
        if(getter==KeyEvent.VK_S){
            downW=false;
        }
        if(getter==KeyEvent.VK_A){
            leftW=false;
        }
        if(getter==KeyEvent.VK_D){
            rightW=false;
        }
        if(getter==KeyEvent.VK_ESCAPE){
            escW=false;
        }
    }
}
