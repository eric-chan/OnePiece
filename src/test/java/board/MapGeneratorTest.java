
package board;
import main_place.MyPanel;
import org.junit.jupiter.api.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MapGeneratorTest {

    private MapGenerator mapGenerator;

    @BeforeEach
    void setUp() throws UnsupportedAudioFileException, IOException {
        mapGenerator = new MapGenerator(new MyPanel(new JFrame()));
    }

    @Test
    void testGetTileSize() {
        assertEquals(50, mapGenerator.getTileSize());
    }

    @Test
    void testGetMapHeight() {
        mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel0);
        assertEquals(18, mapGenerator.getMapHeight());
    }

    @Test
    void testGetMapWidth() {
        mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel0);
        assertEquals(105, mapGenerator.getMapWidth());
    }

    @Test
    void testSetTileOn() {
        mapGenerator.setTileOn(1, 1, MapGenerator.ME_WALL);
        assertEquals(MapGenerator.ME_WALL, mapGenerator.getTileOn(1, 1));
    }

    @Test
    void testSetTileOnXIsNegative() {
        mapGenerator.setTileOn(-1, 0, 0);
        assertEquals(1, mapGenerator.getTileOn(-1, 0));
    }


    @Test
    void testSetTileOnYIsGreaterThanMapHeight() {
        //mapGenerator.setTileOn(0, mapGenerator.getMapHeight() + 1, MapGenerator.ME_GRASS);
        assertEquals(1, mapGenerator.getTileOn(0, mapGenerator.getMapHeight() + 1));
    }

    @Test
    void testGetTileOn() {
        mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel0);
        assertEquals(MapGenerator.ME_WALL, mapGenerator.getTileOn(0, 0));
        assertEquals(MapGenerator.ME_WALL, mapGenerator.getTileOn(0, 1));
        assertEquals(MapGenerator.ME_WALL, mapGenerator.getTileOn(1, 0));
        assertEquals(MapGenerator.ME_GRASS, mapGenerator.getTileOn(1, 1));
    }

    @Test
    void testGetTileOnXIsNegative() {
        int x = -1;
        int y = 0;
        int expected = MapGenerator.ME_WALL;
        int actual = mapGenerator.getTileOn(x, y);
        assertEquals(expected, actual);
    }

    @Test
    void testGetTileOnXIsGreaterThanMapWidth() {
        int x = mapGenerator.getMapWidth() + 1;
        int y = mapGenerator.getMapHeight() / 2;
        assertEquals(MapGenerator.ME_WALL, mapGenerator.getTileOn(x, y));
    }

    @Test
    void testGetTileOnYIsNegative() {
        int result = mapGenerator.getTileOn(0, -1);
        assertEquals(MapGenerator.ME_WALL, result);
    }

    @Test
    void testGetTileOnYIsGreaterThanMapHeight() {
        int result = mapGenerator.getTileOn(0, mapGenerator.getMapHeight() + 1);
        assertEquals(MapGenerator.ME_WALL, result);
    }

    @Test
    void testUpdateMapImage() {
        mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel0);
        mapGenerator.loadTileImage();
        mapGenerator.updateMapImage(MapGenerator.tileSize);
        assertNotNull(mapGenerator.MapImage);
    }

    @Test
    void testLoadTileImage() {
        mapGenerator.loadTileImage();
        assertEquals(50, mapGenerator.tile.length);
    }

    @Test
    void testLoadTileImageFileNotFound() {
        mapGenerator.loadTileImage();
        assertNotNull(mapGenerator.tile[0].image);
    }


    @Test
    void testFillInPanel() {
        mapGenerator.loadLevelFromFile(MapGenerator.MapFileLevel0);
        mapGenerator.loadTileImage();
        mapGenerator.updateMapImage(MapGenerator.tileSize);
        assertEquals(105, mapGenerator.getMapWidth());
        assertEquals(18, mapGenerator.getMapHeight());
    }




}

