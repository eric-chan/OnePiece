package main_place;

import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyPanelTest {
    @Test
    void testFPS() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        int inputFPS = mp.getFPS();
        System.out.println("Testing FPS rates...");
        assertEquals(120,inputFPS);
    }
    @Test
    void testGameState() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        System.out.println("Testing default Game State settings...");
        assertEquals(0,mp.GameState);
        assertEquals(-1,mp.GameStatePast);
    }
    @Test
    void testCoinDiamondNum() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        System.out.println("Testing coin and diamond initial Number...");
        assertEquals(0,mp.coinNumber);
        assertEquals(0,mp.diamondNumber);
    }
    @Test
    void testGameRun() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        System.out.println("Testing Game Running Status...");
        assertEquals(true,mp.gameRun);
    }

}
