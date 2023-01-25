package characters;

import characters.GameObject;
import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {
    @Test
    void testGameObjectInitial() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        float x, y;
        x = 1.0f;
        y = 1.0f;
        GameObject gameobj = new GameObject(mp, x, y);
        assertEquals(1.0, gameobj.getX());
        assertEquals(1.0, gameobj.getY());
        assertEquals(false, gameobj.collisionOn);
        assertEquals(true, gameobj.isAlive);
    }

    @Test
    void testSetX() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        GameObject gameobj = new GameObject(mp);
        gameobj.setX(0.0f);
        assertEquals(0.0f, gameobj.getX());
    }

    @Test
    void testSetY() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        GameObject gameobj = new GameObject(mp);
        gameobj.setY(2.0f);
        assertEquals(2.0f, gameobj.getY());
    }

    @Test
    void testGetX() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        float x, y;
        x = 1.0f;
        y = 1.0f;
        GameObject gameobj = new GameObject(mp, x, y);
        assertEquals(1.0f, gameobj.getX());
    }

    @Test
    void testGetY() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        float x, y;
        x = 1.0f;
        y = 2.0f;
        GameObject gameobj = new GameObject(mp, x, y);
        assertEquals(2.0f, gameobj.getY());
    }

    @Test
    void testgetCurrentImage() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        GameObject gameobj = new GameObject(mp);
        boolean bool;
        if (gameobj.getCurrentImage() == null){
            bool = true;
        }
        else {
            bool = false;
        }
        assertEquals(true, bool);
    }

}
