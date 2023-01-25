package characters;

import main_place.MyPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


/**
 * Creates dynamic enemies
 * Extends the character class
 */
public class Monster extends DynamicObject {

    public BufferedImage up1,l1,r1,down1;
    public BufferedImage[] image_arry = new  BufferedImage [4];

    private Player main_charater;
    private static final float MaxAttackDistance = 1.0f;
    private static final float MaxTrackingDistance = 8.5f; //recommend larger than camera view distance...
    private static final float MaxSpeed = 0.0625f * 0.5f;

    private static final long RandomMovementSpeedUpdateInterval = 500;  //ms
    private long lastRandomMovementUpdateTS = 0;
    private static final float MaxRandomMovementSpeed = 0.0625f * 0.1f;
    private float randomSpeedX = 0f;
    private float randomSpeedY = 0f;

    public long getLastRandomMovementUpdateTS() {
        return lastRandomMovementUpdateTS;
    }

    public float getRandomSpeedX() {
        return randomSpeedX;
    }

    public float getRandomSpeedY() {
        return randomSpeedY;
    }

    public Monster(MyPanel mp1, int x, int y, Player player) {
        super(mp1, x, y);
        loadImages();
        this.main_charater = player;
    }


    /**
     * Obtains different sprites depending on which way
     * the dynamic enemy is facing and uses them appropriately
     */
    private void loadImages() {
        try {

            image_arry[1] = ImageIO.read(new File("src/main/java/image/enemy/Down.png"));

            image_arry[2] = ImageIO.read(new File("src/main/java/image/enemy/Left.png"));

            image_arry[3] = ImageIO.read(new File("src/main/java/image/enemy/Right.png"));

            image_arry[0]= ImageIO.read(new File("src/main/java/image/enemy/Up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Pathfinding method that controls the movement of the
     * dynamic enemies. Varies based on distance from the player, etc.
     */
    public void update() {
        speedX = 0f;    //reset speed
        speedY = 0f;
        float dist = getDist_possessCheck();

        //if outside tracking distance, move at 1/2 speed
        if(dist <= MaxTrackingDistance){
            setSpeedApproachToAlongAxis(mp.player, MaxSpeed);
        }else{
            setSpeedApproachTo(mp.player, MaxSpeed / 2f);
        }

        //apply random movement
        if(System.currentTimeMillis() - lastRandomMovementUpdateTS > RandomMovementSpeedUpdateInterval){
            lastRandomMovementUpdateTS = System.currentTimeMillis();
            applyRandomMovement();

        }
        speedX += randomSpeedX;
        speedY += randomSpeedY;

        CheckCollisionTile(this);

        //apply speed
        x += speedX;
        y += speedY;
    }

    /**
     *Getter for getDistance to Player
     *
     * Also checks if Player is possessed
     * Player is possessed when any monster reaches player
     * The game ends if possessed
     */
    private float getDist_possessCheck() {
        float dist = getDistTo(mp.player);
        if(dist <= MaxAttackDistance){
            mp.GameState = 2;   //player gets attack, end game
        }
        return dist;
    }

    public BufferedImage getCurrentImage() {
        BufferedImage image = null;
        switch (this.getDirection()) {
            case "up":
                image = image_arry[0];
                break;
            case "down":
                image = image_arry[1];
                break;
            case "left":
                image = image_arry[2];
                break;
            case "right":
                image = image_arry[3];
                break;
        }
        return image;
    }



    // try approach to other object by changing self speed
    private void setSpeedApproachTo(GameObject other, float maxSpeed) {
        float dX = other.x - x;
        float dY = other.y - y;
        speedX = dX > 0 ? maxSpeed : -maxSpeed;
        speedY = dY > 0 ? maxSpeed : -maxSpeed;
    }

    //X axis first
    private void setSpeedApproachToAlongAxis(GameObject other, float maxSpeed) {
        float dX = other.x - x;
        float dY = other.y - y;
        if(Math.abs(dX) > 0.5f)
            speedX = dX > 0 ? maxSpeed : -maxSpeed;
        if(Math.abs(dY) > 0.5f)
            speedY = dY > 0 ? maxSpeed : -maxSpeed;

        if(Math.abs(dX) > Math.abs(dY))
            speedY /= 4f;
        else
            speedX /= 4f;
    }

    private void applyRandomMovement(){
        var rnd = new Random();
        randomSpeedX = (rnd.nextBoolean() ? 1 : -1) * MaxRandomMovementSpeed;
        randomSpeedY = (rnd.nextBoolean() ? 1 : -1) * MaxRandomMovementSpeed;
    }
}
