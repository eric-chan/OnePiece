package characters;

import main_place.MyPanel;

import java.awt.image.BufferedImage;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;


/**
 * Character class represents a character in the game
 * Used for main character and dynamic enemies
 * Sets up keyboard input, health bar, and collision properties
 */
public class GameObject {
    protected MyPanel mp;              //JPanel reference for everyone!
    protected float x ,y;
    public boolean collisionOn = false;
    //Indicates whether this object should be updated, if false then skip update! Handled by My_panel
    public boolean isAlive = true;      //Object live time, false to remove, called by update

    public GameObject(MyPanel mp){
        this.mp=mp;
    }
    public GameObject(MyPanel mp, float x, float y){
        this.mp=mp;
        this.x = x;
        this.y = y;
    }

    public void update(){
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public BufferedImage getCurrentImage(){
        return null;
    }

    protected float getDistTo(GameObject other){
        return getDist(this.x, this.y, other.x, other.y);
    }
    
    protected float getDist(float x1, float y1, float x2, float y2) {
        float res = (float)sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return res;
    }

    /**
     * Determines if the collision rectangles on the object interfere and collide with the
     * rectangles on the player sprite
     * simplified and moved from class: CollisionOnChecker.
     * @param other Does it collide with the player?
     * @param maxDistX bouding box X.
     * @param maxDistY bouding box Y.
     */
    protected boolean isBoxCollidingWith(GameObject other, float maxDistX, float maxDistY){
        float dX = other.x - this.x;
        float dY = other.y - this.y;
        return (Math.abs(dX) < maxDistX && Math.abs(dY) < maxDistY);
    }

    protected boolean isInSameTileWith(GameObject other){
        return isBoxCollidingWith(other, 1,1);
    }



}