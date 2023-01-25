package board;

import characters.Monster;
import characters.Trape;
import main_place.MyPanel;
import objects.Diamond;
import objects.GoldenCoin;
import objects.House;
import objects.Platform;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapGenerator {
    MyPanel panel;
    public static final int tileSize = 50;
    public Tile[] tile;
    public int[][] MapTileTypeMatrix;


    protected int mapWidth;
    protected int mapHeight;

    //Map Element ENUM (like enum but can handle 'monster on water' situation)
    public static final int ME_GRASS = 0;             //AIR

    public static final int ME_WALL = 1;
    public static final int ME_WATER = 2;
    public static final int ME_Sand = 3;
    public static final int ME_Tree1 = 4;
    public static final int ME_Tree2= 5;
    public static final int ME_UNWALKABLEGRASS = 7;
    public static final int ME_BRIDGE1 = 8;
    public static final int ME_BRIDGE2 = 9;


    public static final int ME_Flower1 = 11;
    public static final int ME_Flower2 = 12;

    public static final int ME_TRAPE = 15;
    public static final int ME_MONSTER =16 ;
    public static final int ME_DIAMOND = 17;
    public static final int ME_GOLDENCOIN = 18;

    public static final int ME_Fence_y = 20;
    public static final int ME_Fence_x = 21;
    public static final int fance_right_up_cornner = 22;
    public static final int  fance_left_up_cornner= 23;
    public static final int fance_right_down_cornner = 24;
    public static final int  fance_left_down_cornner= 25;

    public static final int ME_HOUSE1 = 101;
    public static final int ME_HOUSE2 = 102;
    public static final int ME_PLATFORM = 111;

    public static final String MapFileLevel0 = "level0.txt";
    public static final String MapFileLevel1 = "level1.txt";
    public BufferedImage MapImage;


    public MapGenerator(MyPanel panel){

        this.panel = panel;
        tile = new Tile[tileSize];

        mapWidth = 0;
        mapHeight = 0;

        loadTileImage();
    }

    private void fillInPanel(MyPanel panel, int meCode, int x, int y) {
        MapTileTypeMatrix[y][x] = 0;
        switch (meCode){
            case ME_GRASS:
                break;
            case ME_WALL:
                MapTileTypeMatrix[y][x] = 1;
                break;
            case ME_WATER:
                MapTileTypeMatrix[y][x] = 2;
                break;
            case ME_Sand:
                MapTileTypeMatrix[y][x] = 3;
                break;
            case ME_Tree1:
                MapTileTypeMatrix[y][x] = 4;
                break;
            case ME_Tree2:
                MapTileTypeMatrix[y][x] = 5;
                break;
            case ME_UNWALKABLEGRASS:
                MapTileTypeMatrix[y][x] = 7;
                break;
            case ME_BRIDGE1:
                MapTileTypeMatrix[y][x] = 8;
                break;
            case ME_BRIDGE2:
                MapTileTypeMatrix[y][x] = 9;
                break;
            case ME_Fence_y:
                MapTileTypeMatrix[y][x] = 20;
                break;
            case ME_Fence_x:
                MapTileTypeMatrix[y][x] = 21;
                break;
            case fance_right_up_cornner:
                MapTileTypeMatrix[y][x] = 22;
                break;
            case fance_left_up_cornner:
                MapTileTypeMatrix[y][x] = 23;
                break;
            case fance_right_down_cornner:
                MapTileTypeMatrix[y][x] = 24;
                break;
            case fance_left_down_cornner:
                MapTileTypeMatrix[y][x] = 25;
                break;
            case ME_Flower1:
                MapTileTypeMatrix[y][x] = 11;
                break;
            case ME_Flower2:
                MapTileTypeMatrix[y][x] = 12;
                break;
            case ME_TRAPE:
                panel.L_Trape.add(new Trape(panel, x, y));
                break;
            case ME_MONSTER:
                panel.L_Monsters.add(new Monster(panel, x, y, panel.player));
                break;
            case ME_DIAMOND:
                panel.L_Rewards.add(new Diamond(panel, x, y));
                break;
            case ME_GOLDENCOIN:
                panel.L_Rewards.add(new GoldenCoin(panel, x, y));
                break;
            case ME_HOUSE1:
                panel.L_SEObjects.add(new House(panel, x, y, 3, 3, "Blue"));
                break;
            case ME_HOUSE2:
                panel.L_SEObjects.add(new House(panel, x, y, 3, 3, "Red"));
                break;
            case ME_PLATFORM:
                panel.L_SEObjects.add(new Platform(panel, x, y, 3, 3, ""));
                break;
        }
    }

    private void resetMyPanelMapData(){
        for (var seo : panel.L_SEObjects)
            seo.isAlive = false;
        for (var m : panel.L_Monsters)
            m.isAlive = false;
        for (var r : panel.L_Rewards)
            r.isAlive = false;
        for (var t : panel.L_Trape)
            t.isAlive = false;
    }

    // return a the list matrix back from the txt
    public void loadLevelFromFile(String path){
        //reset all the stuff first.
        mapWidth = 0;
        mapHeight = 0;
        List<int[]> l_MEMatrix;             //dynamic 2d matrix, to get: l.get(y)[x]
        l_MEMatrix = new ArrayList<>();
        resetMyPanelMapData();

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(path));
            String strLn;
            //Consider use json func in the future.
            while ((strLn = bReader.readLine()) != null){
                //Per line handling
                //Format: int, int, int, .... , int
                var arrStrLn = strLn.split(",");
                var arrMEInt = new int[arrStrLn.length];    //no need to ignore final comma
                for (int i = 0; i < arrStrLn.length; i++)
                    arrMEInt[i] = Integer.parseInt(arrStrLn[i]);

                if(mapWidth == 0)
                    mapWidth = arrMEInt.length;
                else if(mapWidth != arrMEInt.length)
                    System.err.println("ERROR: Map Width different when load!");
                l_MEMatrix.add(arrMEInt);
            }
            mapHeight = l_MEMatrix.size();
            //create tilematrix
            MapTileTypeMatrix = new int[mapHeight][mapWidth];
            //Import into panel
            for(int y = 0; y < mapHeight; y++){
                for(int x = 0; x < mapWidth; x++){
                    fillInPanel(panel, l_MEMatrix.get(y)[x], x, y);
                }
            }

            //override tile type for static objects (house etc)
            for(var seo : panel.L_SEObjects)
                seo.ApplyMapTileCollision(this);

            System.out.printf("Successfully added tile size: x: %d, y: %d%n", mapWidth, mapHeight);

        }catch (FileNotFoundException ex){
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }catch(IOException ex){
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }



    public void loadTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/main/java/image/map/grass00.png"));
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/main/java/image/map/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/main/java/image/map/water00.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/main/java/image/map/Sand.png"));
            tile[3].collision = false;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/main/java/image/map/Tree2.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("src/main/java/image/map/Tree1.png"));
            tile[5].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("src/main/java/image/map/grass00.png"));
            tile[7].collision = true;





            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new File("src/main/java/image/map/bridge_x.png"));
            tile[8].collision = false;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new File("src/main/java/image/map/bridge_y.png"));
            tile[9].collision = false;



            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new File("src/main/java/image/map/flower1.png"));
            tile[11].collision = false;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new File("src/main/java/image/map/flower2.png"));
            tile[12].collision = false;



            // fance
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(new File("src/main/java/image/map/fance_y.png"));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(new File("src/main/java/image/map/fance_x.png"));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(new File("src/main/java/image/map/fancy_right_up_corner.png"));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(new File("src/main/java/image/map/fance_left_up_cornner.png"));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(new File("src/main/java/image/map/fancy_right_up_corner.png"));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(new File("src/main/java/image/map/fance_fown_up_cornner.png"));
            tile[25].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the map image based on the tiles above
     * map is seen as a 2d array (rows, columns)
     */
    public void updateMapImage(int pixelPerTile) {
        var img = new BufferedImage(mapWidth * pixelPerTile,
                mapHeight * pixelPerTile,
                BufferedImage.TYPE_INT_RGB);
        var graphics = img.createGraphics();
        for (int y = 0; y < mapHeight;y++){
            for (int x = 0;x < mapWidth;x++){

                var t = tile[getTileOn(x,y)];
                if(t != null)
                    graphics.drawImage(t.image, x * pixelPerTile, y * pixelPerTile, pixelPerTile, pixelPerTile, null);
            }
        }
        graphics.dispose();
        MapImage = img;
    }


    public int getTileOn(int x, int y) {
        if(x <= 0 || x > mapWidth)
            return ME_WALL;
        if(y <= 0 || y > mapWidth)
            return ME_WALL;
        //return l_MEMatrix.get(y)[x];
        return MapTileTypeMatrix[y][x];
    }

    public void setTileOn(int x, int y, int tileId) {
        if(x <= 0 || x > mapWidth)
            return;
        if(y <= 0 || y > mapWidth)
            return;
        //l_MEMatrix.get(y)[x] = tileId;
        MapTileTypeMatrix[y][x] = tileId;
    }


    // Get map element X count (not graphics width)
    public int getMapWidth(){ return mapWidth;}
    // Get map element Y count (not graphics height)
    public int getMapHeight(){ return mapHeight;}
    public int getTileSize(){ return tileSize;}

}
