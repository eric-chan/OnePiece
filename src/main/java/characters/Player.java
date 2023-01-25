package characters;

import main_place.Keyreader;
import main_place.MyPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Initializes playable character
 */
public class Player extends DynamicObject{
    private Keyreader Kr;
    public BufferedImage[] image_arry = new  BufferedImage [16];
    public static final int ImageSwapPerUpdate = 10;       //Change movement image when how many update (default: 10)
    public static final float PlayerMoveSpeed = 0.0625f;

    public Player(MyPanel mp1, Keyreader kP2){
        super(mp1);
        Kr = kP2;
        setdefault();
        loadImages();
    }
    public void setdefault(){
        this.setX(12);
        this.setY(12);

        fullHPvalue = 6;
        currHPvalue = fullHPvalue;
    }

    /**
     * Updates player position based on keyboard input
     * Four directions, up down left and right
     */
    public void update () {
        if (this.currHPvalue == 0)
            this.mp.GameState = 2;

        if(this.speedX != 0 || this.speedY != 0)
            walkcounter++;
        else
            walkNumber = 1;
        if (walkcounter > ImageSwapPerUpdate) {
            walkcounter = 0;
            walkNumber = walkNumber >= 4 ? 1 : (walkNumber + 1);
        }
        this.x += this.speedX;
        this.y += this.speedY;
    }

    public void updatePlayerMovementByKeys(Keyreader kr){
        this.speedX = 0;
        this.speedY = 0;
        this.speedY += kr.upW ? -PlayerMoveSpeed : 0;
        this.speedY += kr.downW ? PlayerMoveSpeed : 0;
        this.speedX += kr.leftW ? -PlayerMoveSpeed : 0;
        this.speedX += kr.rightW ? PlayerMoveSpeed : 0;

        CheckCollisionTile(this);

        // force move along axis, remove if not satisfied w
        if(Math.abs(this.speedX) >= Math.abs(this.speedY) && Math.abs(this.speedY) > 0)
            speedX = 0f;

    }



    public BufferedImage getCurrentImage() {
        BufferedImage image= null;
        switch (this.getDirection()) {
            case "up":
                if(walkNumber==1)
                    image = image_arry[0];
                if(walkNumber==2)
                    image = image_arry[1];
                if(walkNumber==3)
                    image = image_arry[2];
                if(walkNumber==4)
                    image = image_arry[3];
                break;
            case "down":
                if(walkNumber==1)
                    image = image_arry[4];
                if(walkNumber==2)
                    image = image_arry[5];
                if(walkNumber==3)
                    image = image_arry[6];
                if(walkNumber==4)
                    image = image_arry[7];
                break;
            case "left":
                if(walkNumber==1)
                    image = image_arry[8];
                if(walkNumber==2)
                    image = image_arry[9];
                if(walkNumber==3)
                    image = image_arry[10];
                if(walkNumber==4)
                    image = image_arry[11];
                break;
            case "right":
                if(walkNumber==1)
                    image = image_arry[12];
                if(walkNumber==2)
                    image = image_arry[13];
                if(walkNumber==3)
                    image = image_arry[14];
                if(walkNumber==4)
                    image = image_arry[15];
                break;
            default:
                break;
        }
        return image;
    }


    /**
     * Sets up sprite of player movement in game
     * Various different images are for the different positions the
     * character will be in (ex. walking up on the board, walking to the
     * right on the board, etc.)
     */
    public void loadImages(){
        try{

            image_arry[0] =ImageIO.read(new File("src/main/java/image/player/walkUp1.png"));
            image_arry[1] =ImageIO.read(new File("src/main/java/image/player/walkUp2.png"));
            image_arry[2] =ImageIO.read(new File("src/main/java/image/player/walkUp3.png"));
            image_arry[3] =ImageIO.read(new File("src/main/java/image/player/walkUp4.png"));

            image_arry[4]=ImageIO.read(new File("src/main/java/image/player/walkDown1.png"));
            image_arry[5]=ImageIO.read(new File("src/main/java/image/player/walkDown2.png"));
            image_arry[6]=ImageIO.read(new File("src/main/java/image/player/walkDown3.png"));
            image_arry[7]=ImageIO.read(new File("src/main/java/image/player/walkDown4.png"));

            image_arry[8]=ImageIO.read(new File("src/main/java/image/player/walkLeft1.png"));
            image_arry[9]=ImageIO.read(new File("src/main/java/image/player/walkLeft2.png"));
            image_arry[10]=ImageIO.read(new File("src/main/java/image/player/walkLeft3.png"));
            image_arry[11]=ImageIO.read(new File("src/main/java/image/player/walkLeft4.png"));

            image_arry[12]=ImageIO.read(new File("src/main/java/image/player/walkRight1.png"));
            image_arry[13]=ImageIO.read(new File("src/main/java/image/player/walkRight2.png"));
            image_arry[14]=ImageIO.read(new File("src/main/java/image/player/walkRight3.png"));
            image_arry[15]=ImageIO.read(new File("src/main/java/image/player/walkRight4.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public boolean MainPlayer_is_dead () {
        if (currHPvalue <= 0)
            return true;
        return false;
    }

    public Keyreader getKr() {
        return Kr;
    }
}
