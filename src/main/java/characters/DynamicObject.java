package characters;

import main_place.MyPanel;

public class DynamicObject extends GameObject implements Collision{
    public int fullHPvalue;
    public int currHPvalue;
    public int walkNumber = 1;
    public int walkcounter = 0;
    public int point = 0;
    protected float speedX = 0;
    protected float speedY = 0;

    // setters here !!
    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }



    public DynamicObject(MyPanel mp) {
        super(mp);
    }
    public DynamicObject(MyPanel mp, int x, int y) {
        super(mp, x, y);
    }

    //TODO: Determine dynamic objects' direction using speed instead of string to inc performance...
    public String getDirection() {
        if(Math.abs(speedX) > Math.abs(speedY)){
            if(speedX > 0)
                return "right";
            else
                return "left";
        }else{
            if(speedY < 0)
                return "up";
            else
                return "down";
        }
    }


    public void PointUpdate() {
        point = this.mp.coinNumber + this.mp.diamondNumber * 2;
        if (this.mp.L_Rewards.size() == 0){
            this.mp.GameState = 2;
            this.mp.endUI.isLoose = true;
        }
    }
}
