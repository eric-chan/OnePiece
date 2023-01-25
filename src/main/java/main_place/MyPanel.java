package main_place;

import board.MapGenerator;
import characters.*;
import objects.Reward;
import objects.StaticEnvironmentalObject;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Defines essential items and initiates them in order for the game to run
 * Important variables such as frames per second and size of the board are
 * created here
 * Sets flow for the game
 */
public class MyPanel extends JPanel implements Runnable {
    //Settings
    private final int FPS = 120;

    public int getFPS() {
        return FPS;
    }

    //Logic
    public JFrame frame;
    public Thread game_thread;
    public Keyreader keyreader = new Keyreader();
    public int GameState = 0;
    public int GameStatePast = -1;
    public boolean gameRun = true;

    //Map generator
    public MapGenerator mapGenerator;       //TODO: TileManager (or MapGenerator) should init after (or inside)My_Panel's init


    //Characters
    public Player player= new Player(this, keyreader);
    public List<Monster> L_Monsters = new ArrayList<Monster>();
    public List<Trape> L_Trape = new ArrayList<Trape>();
    public List<Reward> L_Rewards = new ArrayList<Reward>();

    public List<StaticEnvironmentalObject> L_SEObjects = new ArrayList<>();

    //UI
    public StartUI startUI = new StartUI(this, keyreader);
    public UI ui = new UI(this);
    public EndUI endUI = new EndUI(this, keyreader);
    public paintHPValue HP = new paintHPValue(this,player);

    //Camera
    public CameraController cameraCtl = new CameraController(this);

    //WindowSize change checker
    public Dimension WindowSizePast = new Dimension(0,0);

    //Player status:
    public int coinNumber = 0;
    public int diamondNumber = 0;

    //Sound
    public Sound sound = new Sound();


    //setting game panel
    public MyPanel(JFrame frame) throws IOException, UnsupportedAudioFileException {
        super();
        this.frame = frame;
        this.setPreferredSize(new Dimension(16*3*16,12*3*16));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyreader);
        this.setFocusable(true);

        //init
        mapGenerator = new MapGenerator(this);
    }


    public void update() throws IOException, UnsupportedAudioFileException {
        gameStateChangeListener();

        if(GameState == 0){
            player.setSpeedX(0.0625f * 0.5f);
            player.update();
            startUI.update();
            if(player.getX() > 90.0f)
                player.setX(10.0f);
            for (Monster m : L_Monsters)
                m.update();
        }
        if(GameState == 1){
            player.PointUpdate();
            player.updatePlayerMovementByKeys(keyreader);
            player.update();
            for (Monster m : L_Monsters)
                m.update();
            for (Trape t : L_Trape)
                t.update();
            for (Reward r: L_Rewards)
                r.update();

            //Dispose entities
            L_Monsters.removeIf(m->!m.isAlive);
            L_Trape.removeIf(t->!t.isAlive);
            L_Rewards.removeIf(r->!r.isAlive);
            L_SEObjects.removeIf(r->!r.isAlive);
        }
       if(GameState ==2) {
           endUI.update();
       }
    }


    //Paint (in loop while thread)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gameStateChangeListener();

        if(!WindowSizePast.equals(frame.getSize())){    //Catch window size change
            this.cameraCtl.setPixelPerTile(frame.getHeight() / cameraCtl.TileViewY / 2);
            this.mapGenerator.updateMapImage(this.cameraCtl.getPixelPerTile());

            //Lock width to height with ratio.
            frame.setSize(frame.getHeight() / cameraCtl.TileViewY * cameraCtl.TileViewX,
                    frame.getHeight());
            WindowSizePast = frame.getSize();
        }


        if(GameState == 0){
            cameraCtl.draw(g2);
            startUI.draw(g2);
        }
        if(GameState ==1){
            //Draw level stuff
            cameraCtl.draw(g2);

            //Draw UI
            ui.draw(g2);
            HP.presentHPValue(g2);

            g2.dispose();
        }
        if(GameState == 2)
            cameraCtl.draw(g2);
            endUI.draw(g2);
    }

    public void gameStateChangeListener(){
        if(GameStatePast != GameState) {    //Catch state changing.
            GameStatePast = GameState;
            switch (GameState){
                case 0: //Game init
                    mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel0);
                    mapGenerator.updateMapImage(cameraCtl.getPixelPerTile());
                    cameraCtl.setFocus(this.player);
                    player.setX(10.0f);
                    player.setY(9.0f);
                    break;
                case 1: //Game loop run
                    mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel1);
                    mapGenerator.updateMapImage(cameraCtl.getPixelPerTile());
                    cameraCtl.setFocus(this.player);
                    player.setdefault();
                    break;
                case 2: //Player lost or win, end game
                    endUI.setDefault();
                    break;
                default:    //Fail check...
                    break;
            }
        }
    }

    public void start_thread(){
        game_thread = new Thread(this);
        game_thread.start();
    }

    /**
     * Starts the game
     */
    @Override
    public void run() {
        double interval = 1000000000/FPS;
        double the_change = 0;
        double last_time = System.nanoTime();
        long now_time;

        sound.PLayMusic(0);

        while (game_thread!=null&&this.gameRun){
            now_time = System.nanoTime();
            the_change += (now_time-last_time)/interval;
            last_time = now_time;
            if(the_change>1){

                // check endui.updata2();
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                repaint();
                the_change--;
            }
        }
    }

    // music


}
