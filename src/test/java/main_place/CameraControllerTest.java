package main_place;

import main_place.CameraController;
import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CameraControllerTest {


    @Test
    void testTileView() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        CameraController cctrol = new CameraController(mp);
        assertEquals(8, cctrol.TileViewX);
        assertEquals(5, cctrol.TileViewY);
    }
    @Test
    void testGetPixelPerTile() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        CameraController cctrol = new CameraController(mp);
        int pxlPerTile = cctrol.getPixelPerTile();
        assertEquals(48, pxlPerTile);
    }
    @Test
    void testSetPixelPerTile() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        CameraController cctrol = new CameraController(mp);
        cctrol.setPixelPerTile(40);
        int pxlPerTile = cctrol.getPixelPerTile();
        assertEquals(40, pxlPerTile);
    }
}
